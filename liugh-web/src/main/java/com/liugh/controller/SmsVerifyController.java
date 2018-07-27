package com.liugh.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liugh.annotation.Log;
import com.liugh.annotation.Pass;
import com.liugh.base.PublicResult;
import com.liugh.base.PublicResultConstant;
import com.liugh.base.SmsSendResponse;
import com.liugh.entity.SmsVerify;
import com.liugh.service.ISmsVerifyService;
import com.liugh.util.ComUtil;
import com.liugh.util.GenerationSequenceUtil;
import com.liugh.util.SmsSendUtil;
import com.liugh.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 验证码发送记录 前端控制器
 * </p>
 *
 * @author liugh123
 * @since 2018-06-25
 */
@RestController
@RequestMapping("/smsVerify")
@Api(description="短信模块")
public class SmsVerifyController {

    @Autowired
    private ISmsVerifyService smsVerifyService;

    /**
     * smsType 有四种类型：REG/注册账号 FINDPASSWORD/修改密码 AUTH/登陆验证 MODIFYINFO/修改账号
     * @param smsType
     * @param mobile
     * @return
     * @throws Exception
     */
    @ApiOperation(value="获取验证码接口", notes="路径参数,不需要Authorization")
    @GetMapping("/{smsType}/{mobile}")
    @Pass
    @Log(description = "获取短信验证码接口:/smsVerify/{smsType}/{mobile}")
   public PublicResult<SmsVerify> getCaptcha (@PathVariable String smsType,@PathVariable String mobile) throws Exception{
        if(!StringUtil.checkMobileNumber(mobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        String randNum = GenerationSequenceUtil.getRandNum(4);
        SmsSendResponse smsSendResponse = SmsSendUtil.sendMessage(mobile,
                "校验码: " + randNum+"。您正在进行"+SmsSendUtil.SMSType.getSMSType(smsType).toString()+"的操作,请在5分钟内完成验证，注意保密哦！");
        SmsVerify smsVerify = new SmsVerify(smsSendResponse.getMsgId()
                ,mobile,randNum, SmsSendUtil.SMSType.getType(smsType),System.currentTimeMillis());
        smsVerifyService.insert(smsVerify);
        return  new PublicResult<>(PublicResultConstant.SUCCESS, null);
   }



    @ApiOperation(value="验证码验证接口", notes="请求参数,不需要Authorization")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "smsType", value = "验证码类型"
                    , required = true, dataType = "String",paramType="query"),
            @ApiImplicitParam(name = "mobile", value = "手机号"
                    , required = true, dataType = "String",paramType="query"),
            @ApiImplicitParam(name = "captcha", value = "验证码"
                    , required = true, dataType = "String",paramType="query")
    })
    @GetMapping("/captcha/check")
    @Pass
    public PublicResult<Boolean> captchaCheck (@RequestParam String smsType,
            @RequestParam String mobile ,@RequestParam String captcha) throws Exception{
        if(!StringUtil.checkMobileNumber(mobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, false);
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
                captcha,SmsSendUtil.SMSType.getType(smsType));
        if(ComUtil.isEmpty(smsVerifies)){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, false);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, false);
        }
        return  new PublicResult<>(PublicResultConstant.SUCCESS, true);
    }
}

