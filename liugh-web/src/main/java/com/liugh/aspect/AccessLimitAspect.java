package com.liugh.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.liugh.annotation.AccessLimit;
import com.liugh.base.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 限流切面
 * Created by liugh on 2018/10/12.
 */
@Slf4j
public class AccessLimitAspect extends AbstractAspectManager{

    public AccessLimitAspect(AspectApi aspectApi){
        super(aspectApi);
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable {
        super.doHandlerAspect(pjp,method);
        execute(pjp,method);
        return null;
    }

    //添加速率.保证是单例的
    private static RateLimiter rateLimiter = RateLimiter.create(1000);
     //使用url做为key,存放令牌桶 防止每次重新创建令牌桶
    private static  Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Override
    public Object execute(ProceedingJoinPoint pjp,Method method) throws Throwable{
        AccessLimit lxRateLimit = method.getAnnotation(AccessLimit.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 或者url(存在map集合的key)
        String url = request.getRequestURI();
        if (!limitMap.containsKey(url)) {
            // 创建令牌桶
            rateLimiter = RateLimiter.create(lxRateLimit.perSecond());
            limitMap.put(url, rateLimiter);
            log.info("<<=================  请求{},创建令牌桶,容量{} 成功!!!",url,lxRateLimit.perSecond());
        }
        rateLimiter = limitMap.get(url);
        if (!rateLimiter.tryAcquire(lxRateLimit.timeOut(), lxRateLimit.timeOutUnit())) {//获取令牌
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.info("Error ---时间:{},获取令牌失败.", sdf.format(new Date()));
            throw new BusinessException("服务器繁忙，请稍后再试!");
        }
        return null;
    }
}
