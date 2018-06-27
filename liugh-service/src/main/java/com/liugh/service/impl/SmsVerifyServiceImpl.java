package com.liugh.service.impl;

import com.liugh.entity.SmsVerify;
import com.liugh.mapper.SmsVerifyMapper;
import com.liugh.service.ISmsVerifyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
