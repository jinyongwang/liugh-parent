package com.liugh.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liugh.annotation.Log;
import com.liugh.annotation.Pass;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.Constant;
import com.liugh.base.PublicResultConstant;
import com.liugh.config.ResponseHelper;
import com.liugh.config.ResponseModel;
import com.liugh.entity.SmsVerify;
import com.liugh.entity.User;
import com.liugh.service.INoticeService;
import com.liugh.service.IRoleService;
import com.liugh.service.ISmsVerifyService;
import com.liugh.service.IUserService;
import com.liugh.service.impl.MyWebSocketService;
import com.liugh.util.ComUtil;
import com.liugh.util.SmsSendUtil;
import com.liugh.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 *  登录接口
 * @author liugh
 * @since 2018-05-03
 */
@RestController
@Api(description="身份认证模块")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISmsVerifyService smsVerifyService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private INoticeService noticeService;

    @ApiOperation(value="手机密码登录", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"13888888888\",\"password\":\"123456\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/login")
    @Log(description="前台密码登录接口:/login")
    @Pass
    public ResponseModel<Map<String, Object>> login(
            @ValidationParam("mobile,password")@RequestBody JSONObject requestJson) throws Exception{
        //由于 @ValidationParam注解已经验证过mobile和passWord参数，所以可以直接get使用没毛病。
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            return ResponseHelper.validationFailure(PublicResultConstant.MOBILE_ERROR);
        }
        User user = userService.selectOne(new EntityWrapper<User>().where("mobile = {0} and status = 1",mobile));
        if (ComUtil.isEmpty(user) || !BCrypt.checkpw(requestJson.getString("password"), user.getPassword())) {
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
        //测试websocket用户登录给管理员发送消息的例子  前端代码参考父目录下WebSocketDemo.html
        noticeService.insertByThemeNo("themeNo-cwr3fsxf233edasdfcf2s3","13888888888");
        MyWebSocketService.sendMessageTo(JSONObject.toJSONString(user),"13888888888");
        return ResponseHelper.buildResponseModel(result);
    }

    @ApiOperation(value="短信验证码登录", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"13888888888\",\"captcha\":\"5780\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/login/captcha")
    @Log(description="前台短信验证码登录接口:/login/captcha")
    @Pass
    public ResponseModel<Map<String, Object>> loginBycaptcha(
            @ValidationParam("mobile,captcha")@RequestBody JSONObject requestJson) throws Exception{
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            return ResponseHelper.validationFailure(PublicResultConstant.MOBILE_ERROR);
        }
        User user = userService.getUserByMobile(mobile);
        //如果不是启用的状态
        if(!ComUtil.isEmpty(user) && user.getStatus() != Constant.ENABLE){
            return ResponseHelper.validationFailure("该用户状态不是启用的!");
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.AUTH.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        if (ComUtil.isEmpty(user)) {
            //设置默认密码
            User userRegister = User.builder().password(BCrypt.hashpw("123456", BCrypt.gensalt()))
                    .mobile(mobile).username(mobile).build();
            user =userService.register(userRegister, Constant.RoleType.USER);
        }
        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
        return ResponseHelper.buildResponseModel(result);
    }



    @ApiOperation(value="手机验证码注册", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"username\":\"liugh\",\"mobile\":\"13888888888\",</br>" +
                    "\"captcha\":\"5780\",\"password\":\"123456\",</br>\"rePassword\":\"123456\",\"job\":\"java开发\"," +
                    "</br>\"unit(可不传)\":\"xxx公司\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/register")
    @Log(description="注册接口:/register")
    @Pass
    public ResponseModel<User> register(@ValidationParam("username,password,rePassword,mobile,captcha,job")
                                       @RequestBody JSONObject requestJson) {
        //可直接转为java对象,简化操作,不用再set一个个属性
        User userRegister = requestJson.toJavaObject(User.class);
        if(!StringUtil.checkMobileNumber(userRegister.getMobile())){
            return ResponseHelper.validationFailure(PublicResultConstant.MOBILE_ERROR);
        }
        if (!userRegister.getPassword().equals(requestJson.getString("rePassword"))) {
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_RE_PASSWORD);
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(userRegister.getMobile(),
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.REG.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        //验证码是否过期
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        userRegister.setPassword(BCrypt.hashpw(requestJson.getString("password"), BCrypt.gensalt()));
        //默认注册普通用户
        userService.register(userRegister, Constant.RoleType.USER);
        return ResponseHelper.buildResponseModel(userRegister);
    }


    @ApiOperation(value="忘记密码", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"captcha\":\"5780\",</br>" +
                    "\"password\":\"123456\",\"rePassword\":\"123456\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/forget/password")
    @Pass
    public ResponseModel<String> resetPassWord (@ValidationParam("mobile,captcha,password,rePassword")
                                               @RequestBody JSONObject requestJson ) throws Exception{
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            return ResponseHelper.validationFailure(PublicResultConstant.MOBILE_ERROR);
        }
        if (!requestJson.getString("password").equals(requestJson.getString("rePassword"))) {
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_RE_PASSWORD);
        }
        User user = userService.getUserByMobile(mobile);
        roleService.getRoleIsAdminByUserNo(user.getUserNo());
        if(ComUtil.isEmpty(user)){
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_USER);
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.FINDPASSWORD.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        user.setPassword(BCrypt.hashpw(requestJson.getString("password"),BCrypt.gensalt()));
        userService.updateById(user);
        return ResponseHelper.buildResponseModel(null);
    }

    /**
     * 检查用户是否注册过
     * @param mobile
     * @return
     * @throws Exception
     */
    @GetMapping("/check/mobile")
    @Pass
    @ApiIgnore
    public ResponseModel loginBycaptcha(@RequestParam("mobile") String mobile) throws Exception{
        User user = userService.getUserByMobile(mobile);
        return ResponseHelper.buildResponseModel(!ComUtil.isEmpty(user));
    }
}
