package com.liugh.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liugh.annotation.CurrentUser;
import com.liugh.annotation.ParamXssPass;
import com.liugh.annotation.Pass;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.PageResult;
import com.liugh.base.PublicResult;
import com.liugh.base.PublicResultConstant;
import com.liugh.entity.SmsVerify;
import com.liugh.entity.User;
import com.liugh.service.ISmsVerifyService;
import com.liugh.service.IUserService;
import com.liugh.util.ComUtil;
import com.liugh.util.SmsSendUtil;
import com.liugh.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@RequestMapping("/api/user")
//不加入swagger ui里
@ApiIgnore
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    private ISmsVerifyService smsVerifyService;

    /**
     * 获取当前登录用户信息
     * @param currentUser
     * @return
     * @throws Exception
     */
    @GetMapping("/currentUser")
    public PublicResult<User> findCurrentUser( @CurrentUser User currentUser) throws Exception{
        return new PublicResult<>(PublicResultConstant.SUCCESS, currentUser);
    }

    @PostMapping("/mobile")
    public PublicResult<String> resetMobile(@CurrentUser User currentUser,
                                            @ValidationParam("mobile,captcha")@RequestBody JSONObject requestJson ){
        String newMobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(newMobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        EntityWrapper<SmsVerify> captchaQuery = new EntityWrapper<>();
        captchaQuery.where("mobile={0} and sms_verify={1} and  sms_type = 4",
                newMobile,requestJson.getString("captcha"));
        captchaQuery.orderBy("create_time",false);
        List<SmsVerify> smsVerifies = smsVerifyService.selectList(captchaQuery);
        if(ComUtil.isEmpty(smsVerifies)){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, null);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, null);
        }
        currentUser.setMobile(newMobile);
        userService.updateById(currentUser);
        return new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

    @PostMapping("/password")
    public PublicResult<String> resetPassWord (@CurrentUser User currentUser,@ValidationParam("oldPassWord,passWord,rePassWord")
    @RequestBody JSONObject requestJson ) throws Exception{
        if (!requestJson.getString("passWord").equals(requestJson.getString("rePassWord"))) {
            return new PublicResult<>(PublicResultConstant.INVALID_RE_PASSWORD, null);
        }
        if(!BCrypt.checkpw(requestJson.getString("oldPassWord"),currentUser.getPassWord())){
            return new PublicResult<>(PublicResultConstant.INVALID_USERNAME_PASSWORD, null);
        }
        currentUser.setPassWord(BCrypt.hashpw(requestJson.getString("passWord"),BCrypt.gensalt()));
        userService.updateById(currentUser);
        return  new PublicResult<String>(PublicResultConstant.SUCCESS, null);
    }

    @PostMapping("/info")
    public PublicResult<String> resetUserInfo (@CurrentUser User currentUser,@RequestBody JSONObject requestJson) throws Exception{
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
        return  new PublicResult<String>(PublicResultConstant.SUCCESS, null);
    }

    @GetMapping(value = "/pageList")
    @RequiresPermissions(value = {"user:list"})
    public PublicResult findList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(value = "userName", defaultValue = "",required = false) String userName) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        if (!ComUtil.isEmpty(userName)) {
            ew.like("user_name", userName);
        }
        Page<User> page = userService.selectPage(new Page<>(pageIndex, pageSize), ew);
        return new PublicResult<PageResult>(PublicResultConstant.SUCCESS, new PageResult<>(
                page.getTotal(), pageIndex, pageSize, page.getRecords()));
    }
}

