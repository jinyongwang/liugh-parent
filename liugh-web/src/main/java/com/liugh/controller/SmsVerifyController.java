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
@RequestMapping("/api/smsVerify")
public class SmsVerifyController {

    @Autowired
    private ISmsVerifyService smsVerifyService;

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


    @GetMapping("/captcha/check")
    @Pass
    public PublicResult<Boolean> captchaCheck (@RequestParam String smsType,
            @RequestParam String mobile ,@RequestParam String captcha) throws Exception{
        if(!StringUtil.checkMobileNumber(mobile)){
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, false);
        }
        EntityWrapper<SmsVerify> captchaQuery = new EntityWrapper<>();
        captchaQuery.where("mobile={0} and sms_verify={1} and sms_type = {2} ",
                mobile,captcha,SmsSendUtil.SMSType.getType(smsType));
        captchaQuery.orderBy("create_time",false);
        List<SmsVerify> smsVerifies = smsVerifyService.selectList(captchaQuery);
        if(ComUtil.isEmpty(smsVerifies)){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, false);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, false);
        }
        return  new PublicResult<>(PublicResultConstant.SUCCESS, true);
    }
}

