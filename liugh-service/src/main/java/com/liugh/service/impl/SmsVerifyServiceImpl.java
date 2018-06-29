package com.liugh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.liugh.entity.SmsVerify;
import com.liugh.mapper.SmsVerifyMapper;
import com.liugh.service.ISmsVerifyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 验证码发送记录 服务实现类
 * </p>
 *
 * @author liugh123
 * @since 2018-06-25
 */
@Service
public class SmsVerifyServiceImpl extends ServiceImpl<SmsVerifyMapper, SmsVerify> implements ISmsVerifyService {

    @Override
    public List<SmsVerify> getByMobileAndCaptchaAndType(String mobile, String captcha, int type) {
        EntityWrapper<SmsVerify> smsQuery = new EntityWrapper<>();
        smsQuery.where("mobile={0} and sms_verify={1} and  sms_type = {2}",
                mobile,captcha,type);
        smsQuery.orderBy("create_time",false);
        return this.selectList(smsQuery);
    }
}
