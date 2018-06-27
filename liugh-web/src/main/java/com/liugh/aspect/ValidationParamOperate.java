package com.liugh.aspect;

/**
 * @author liugh
 * @since on 2018/5/10.
 */
public class ValidationParamOperate extends AspectHandler{
    @Override
    protected ValidationParam factoryMethod() {
        return  new ValidationParam();
    }
}
