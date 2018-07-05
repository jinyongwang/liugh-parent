package com.liugh.aspect;

/**
 * @author liugh
 * @since on 2018/5/10.
 */
public class RecordLogOperate extends AspectHandler{
    @Override
    protected RecordLogAspect factoryMethod() {
        return new RecordLogAspect();
    }
}
