package com.liugh.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liugh.annotation.CurrentUser;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.Constant;
import com.liugh.base.PublicResultConstant;
import com.liugh.config.ResponseHelper;
import com.liugh.config.ResponseModel;
import com.liugh.entity.SmsVerify;
import com.liugh.entity.User;
import com.liugh.service.ISmsVerifyService;
import com.liugh.service.IUserService;
import com.liugh.util.ComUtil;
import com.liugh.util.SmsSendUtil;
import com.liugh.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liugh
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    private ISmsVerifyService smsVerifyService;

    /**
     * 获取当前登录用户信息
     * @param user
     * @return
     * @throws Exception
     */
    @GetMapping("/currentUser")
    public ResponseModel<User> getUser(@CurrentUser User user) throws Exception{
        return ResponseHelper.buildResponseModel(user);
    }

    @PostMapping("/mobile")
    public ResponseModel<String> resetMobile(@CurrentUser User currentUser,
                                            @ValidationParam("newMobile,captcha")@RequestBody JSONObject requestJson ){
        String newMobile = requestJson.getString("newMobile");
        if(!StringUtil.checkMobileNumber(newMobile)){
            return ResponseHelper.validationFailure(PublicResultConstant.MOBILE_ERROR);
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(newMobile,
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.MODIFYINFO.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return ResponseHelper.validationFailure(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        currentUser.setMobile(newMobile);
        userService.updateById(currentUser);
        return ResponseHelper.buildResponseModel(null);
    }

    @PostMapping("/password")
    public ResponseModel<String> resetPassWord (@CurrentUser User currentUser,@ValidationParam("oldPassWord,passWord,rePassWord")
    @RequestBody JSONObject requestJson ) throws Exception{
        if (!requestJson.getString("passWord").equals(requestJson.getString("rePassWord"))) {
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_RE_PASSWORD);
        }
        if(!BCrypt.checkpw(requestJson.getString("oldPassWord"),currentUser.getPassWord())){
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        currentUser.setPassWord(BCrypt.hashpw(requestJson.getString("passWord"),BCrypt.gensalt()));
        userService.updateById(currentUser);
        return ResponseHelper.buildResponseModel(null);
    }

    /**
     * 管理端修改密码
     * @return
     * @throws Exception
     */
    @PostMapping("/admin/password")
    //拥有超级管理员或管理员角色的用户可以访问这个接口
    @RequiresRoles(value = {Constant.RoleType.SYS_ASMIN_ROLE,Constant.RoleType.ADMIN},logical =  Logical.OR)
    public ResponseModel<String> resetPassWord (@ValidationParam("userNo,passWord,rePassWord")
                                               @RequestBody JSONObject requestJson ) throws Exception{
        User user = userService.selectById(requestJson.getString("userNo"));
        if(ComUtil.isEmpty(user)){
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_USER);
        }
        if (!requestJson.getString("passWord").equals(requestJson.getString("rePassWord"))) {
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_RE_PASSWORD);
        }
        user.setPassWord(BCrypt.hashpw(requestJson.getString("passWord"),BCrypt.gensalt()));
        userService.updateById(user);
        return ResponseHelper.buildResponseModel(null);
    }


    @PostMapping("/info")
    public ResponseModel<User> resetUserInfo (@CurrentUser User currentUser,@RequestBody JSONObject requestJson) throws Exception{
        if(!ComUtil.isEmpty(requestJson.getString("userName"))){
            currentUser.setUserName(requestJson.getString("userName"));
        }
        if(!ComUtil.isEmpty(requestJson.getString("avatar"))){
            currentUser.setAvatar(requestJson.getString("avatar"));
        }
        if(!ComUtil.isEmpty(requestJson.getString("unit"))){
            currentUser.setUnit(requestJson.getString("unit"));
        }
        if(!ComUtil.isEmpty(requestJson.getString("job"))){
            currentUser.setJob(requestJson.getString("job"));
        }
        userService.updateById(currentUser);
        return ResponseHelper.buildResponseModel(currentUser);
    }

    @GetMapping(value = "/pageList")
//    @RequiresPermissions(value = {"user:list"})
    public ResponseModel<Page<User>> findList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(value = "userName", defaultValue = "",required = false) String userName) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        if (!ComUtil.isEmpty(userName)) {
            ew.like("user_name", userName);
        }
        return ResponseHelper.buildResponseModel(userService.selectPage(new Page<>(pageIndex, pageSize), ew));
    }

    @GetMapping("/admin/infoList")
    @ApiOperation(value="获取用户列表", notes="需要header里加入Authorization")
    //拥有超级管理员或管理员角色的用户可以访问这个接口
    @RequiresRoles(value={Constant.RoleType.ADMIN,Constant.RoleType.SYS_ASMIN_ROLE},logical = Logical.OR)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页"
                    , dataType = "String",paramType="query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条"
                    , dataType = "String",paramType="query"),
            @ApiImplicitParam(name = "info", value = "会员名称或者电话"
                    , dataType = "String",paramType="query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间"
                    , dataType = "Long",paramType="query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间"
                    , dataType = "Long",paramType="query")
    })
    public ResponseModel findInfoList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                     @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                     //info-->用户名或者电话号码
                                     @RequestParam(name = "info", defaultValue = "", required = false) String info,
                                     @RequestParam(name = "startTime", defaultValue = "", required = false) String startTime,
                                     @RequestParam(name = "endTime", defaultValue = "", required = false) String endTime) throws Exception{
        //启用或禁用的用户
        Integer []  status= {1,2};
        //自定义分页关联查询列表
        Page<User> userPage = userService.selectPageByConditionUser(new Page<User>(pageIndex, pageSize),info,status,
                startTime,endTime);
        return ResponseHelper.buildResponseModel(userPage);
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "userNo", value = "用户ID", required = true, dataType = "String",paramType = "path")
    @GetMapping(value = "/{userNo}")
//    @RequiresPermissions(value = {"user:list"})
    public ResponseModel<User> findOneUser(@PathVariable("userNo") Integer userNo) {
        User user = userService.selectById(userNo);
        return ResponseHelper.buildResponseModel(user);
    }

    @ApiOperation(value="删除用户", notes="根据url的id来删除用户")
    @ApiImplicitParam(name = "userNo", value = "用户ID", required = true, dataType = "String",paramType = "path")
    @DeleteMapping(value = "/{userNo}")
//    @RequiresPermissions(value = {"user:delete"})
    public ResponseModel deleteUser(@PathVariable("userNo") String userNo) {
        User user = userService.selectById(userNo);
        if (ComUtil.isEmpty(user)) {
            return ResponseHelper.validationFailure(PublicResultConstant.INVALID_USER);
        }
        boolean result = userService.deleteByUserNo(userNo);
        return ResponseHelper.buildResponseModel(result);
    }
}

