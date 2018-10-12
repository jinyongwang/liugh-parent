package com.liugh.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author liugh
 * @since on 2018/5/10.
 */
public abstract class AspectManager implements AspectApi{

    public abstract Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable;

}
