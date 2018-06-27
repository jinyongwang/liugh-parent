package com.liugh.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author liugh
 * @since on 2018/5/10.
 */
public interface AspectApi {
     Object doHandlerAspect(Object [] obj , ProceedingJoinPoint pjp, Method method,boolean isAll)throws Throwable;
}
