package com.liugh.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.liugh.annotation.AccessLimit;
import com.liugh.base.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 限流切面
 * Created by liugh on 2018/10/12.
 */
public class AccessLimitAspect extends AspectManager{


    private AspectApi aspectApi;

    public AccessLimitAspect(AspectApi aspectApi){
        super();
        this.aspectApi=aspectApi;
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable {
        aspectApi.doHandlerAspect(pjp,method);
        doRateLimit(method);
        return null;
    }
    //表示最大许可是10,比如每秒有10个许可，那么你获取许可的间隔时间是400毫秒
    private RateLimiter rateLimiter = RateLimiter.create(0.001);


    private void doRateLimit(Method method) throws Exception{
        AccessLimit lxRateLimit = method.getAnnotation(AccessLimit.class);
        rateLimiter.setRate(lxRateLimit.perSecond());
        if (!rateLimiter.tryAcquire(lxRateLimit.timeOut(), lxRateLimit.timeOutUnit())) {
            throw new BusinessException("服务器繁忙，请稍后再试!");
        }
    }
}
