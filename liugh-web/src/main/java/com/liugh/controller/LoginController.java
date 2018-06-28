package com.liugh.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liugh.annotation.Log;
import com.liugh.annotation.Pass;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.PublicResult;
import com.liugh.base.PublicResultConstant;
import com.liugh.entity.SmsVerify;
import com.liugh.entity.User;
import com.liugh.service.ISmsVerifyService;
import com.liugh.service.IUserService;
import com.liugh.util.ComUtil;
import com.liugh.util.GenerationSequenceUtil;
import com.liugh.util.SmsSendUtil;
import com.liugh.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 *  登录接口
 * @author liugh
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISmsVerifyService smsVerifyService;

    @ApiOperation(value="手机密码登录", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"passWord\":\"123456\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/login")
    @Log(description="前台密码登录接口:/login")
    @Pass
    public PublicResult<Map<String, Object>> login(
            @ValidationParam("mobile,passWord")@RequestBody JSONObject requestJson) throws Exception{
        String mobile = requestJson.getString("mobile");
        String passWord = requestJson.getString("passWord");
        if(!StringUtil.checkMobileNumber(mobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.where("mobile={0}", mobile);
        User user = userService.selectOne(ew);
        if (ComUtil.isEmpty(user) || !BCrypt.checkpw(passWord, user.getPassWord())) {
            return new PublicResult<>(PublicResultConstant.INVALID_USERNAME_PASSWORD, null);
        }
        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }

    @ApiOperation(value="短信验证码登录", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"captcha\":\"5780\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/login/captcha")
    @Log(description="前台短信验证码登录接口:/login/captcha")
    @Pass
    public PublicResult<Map<String, Object>> loginBycaptcha(
            @ValidationParam("mobile,captcha")@RequestBody JSONObject requestJson) throws Exception{
        String mobile = requestJson.getString("mobile");
        String captcha = requestJson.getString("captcha");
        if(!StringUtil.checkMobileNumber(mobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.where("mobile={0}", mobile);
        User user = userService.selectOne(ew);
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        EntityWrapper<SmsVerify> captchaQuery = new EntityWrapper<>();
        captchaQuery.where("mobile={0} and sms_verify={1} and sms_type = 1 ",mobile,captcha);
        captchaQuery.orderBy("create_time",false);
        List<SmsVerify> smsVerifies = smsVerifyService.selectList(captchaQuery);
        if(ComUtil.isEmpty(smsVerifies)){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, null);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, null);
        }
        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }



    @ApiOperation(value="手机验证码注册", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"userName\":\"liugh\",\"mobile\":\"17765071662\",</br>" +
                    "\"captcha\":\"5780\",\"passWord\":\"123456\",</br>\"rePassWord\":\"123456\",\"job\":\"java开发\"," +
                    "</br>\"unit(可不传)\":\"天创金农\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/register")
    @Log(description="注册接口:/register")
    @Pass
    public PublicResult<User> register(@ValidationParam("userName,passWord,rePassWord,mobile,captcha,job")
                                       @RequestBody JSONObject requestJson) {
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        if (!requestJson.getString("passWord").equals(requestJson.getString("rePassWord"))) {
            return new PublicResult<>(PublicResultConstant.INVALID_RE_PASSWORD, null);
        }
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.where("mobile={0}",mobile);
        User user = userService.selectOne(ew);
        if (!ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.USERNAME_ALREADY_IN, null);
        }
        EntityWrapper<SmsVerify> captcha = new EntityWrapper<>();
        captcha.where("mobile={0} and sms_verify={1} and sms_type = 2",mobile, requestJson.getString("captcha"));
        captcha.orderBy("create_time",false);
        List<SmsVerify> smsVerifies = smsVerifyService.selectList(captcha);
        if(ComUtil.isEmpty(smsVerifies)){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, null);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, null);
        }
        User userRegister = new User(GenerationSequenceUtil.generateUUID("user"), mobile, requestJson.getString("userName"),
                BCrypt.hashpw(requestJson.getString("passWord"),BCrypt.gensalt()),
                requestJson.getString("job"),System.currentTimeMillis());
        if(!ComUtil.isEmpty(requestJson.getString("unit"))){
            userRegister.setUnit(requestJson.getString("unit"));
        }
        boolean result = userService.register(userRegister, GenerationSequenceUtil.generateUUID("role"));
        return result? new PublicResult<>(PublicResultConstant.SUCCESS, null):
                new PublicResult<>(PublicResultConstant.FAILED, null);
    }


    @ApiOperation(value="忘记密码", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"captcha\":\"5780\",</br>" +
                    "\"passWord\":\"123456\",\"rePassWord\":\"123456\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/forget/password")
    @Pass
    public PublicResult<String> resetPassWord (@ValidationParam("mobile,captcha,passWord,rePassWord")
                                               @RequestBody JSONObject requestJson ) throws Exception{
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        if (!requestJson.getString("passWord").equals(requestJson.getString("rePassWord"))) {
            return new PublicResult<>(PublicResultConstant.INVALID_RE_PASSWORD, null);
        }
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.where("mobile={0}",mobile);
        User user = userService.selectOne(ew);
        if(ComUtil.isEmpty(user)){
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        EntityWrapper<SmsVerify> captchaQuery = new EntityWrapper<>();
        captchaQuery.where("mobile={0} and sms_verify={1} and  sms_type = 3",
                mobile,requestJson.getString("captcha"));
        captchaQuery.orderBy("create_time",false);
        List<SmsVerify> smsVerifies = smsVerifyService.selectList(captchaQuery);
        if(ComUtil.isEmpty(smsVerifies)){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, null);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, null);
        }
        user.setPassWord(BCrypt.hashpw(requestJson.getString("passWord"),BCrypt.gensalt()));
        userService.updateById(user);
        return  new PublicResult<String>(PublicResultConstant.SUCCESS, null);
    }


    @ApiIgnore
    @RequestMapping(path = "/401",produces = "application/json;charset=utf-8")
    public PublicResult<String> unauthorized() {
        return new PublicResult<>(PublicResultConstant.UNAUTHORIZED, null);
    }
}
