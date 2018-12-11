package com.liugh.aspect;

import com.alibaba.fastjson.JSONObject;
import com.liugh.annotation.ValidationParam;
import com.liugh.exception.ParamJsonException;
import com.liugh.util.ComUtil;
import com.liugh.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 验证参数切面
 * @author liugh
 * @since on 2018/5/10.
 */
public class ValidationParamAspect extends AbstractAspectManager{

    public ValidationParamAspect(AspectApi aspectApi){
        super(aspectApi);
    }
    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable{
        super.doHandlerAspect(pjp,method);
        execute(pjp,method);
        return null;
    }


    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable{
       //获取注解的value值返回
        String validationParamValue = StringUtil.getMethodAnnotationOne(method,ValidationParam.class.getSimpleName());
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String requestURI = request.getRequestURI();
        //获取类名上的url
        String url = getMethodUrl(method,request.getContextPath());
        Object[] obj = pjp.getArgs();
        if(requestURI.equals(url)) {
            if (!ComUtil.isEmpty(validationParamValue)) {
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] instanceof JSONObject) {
                        JSONObject jsonObject = JSONObject.parseObject(obj[i].toString());
                        //是否有所有必须参数
                        hasAllRequired(jsonObject, validationParamValue);
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取方法上的url地址
     * @param method
     * @return
     */
    private String getMethodUrl(Method method,String contextPath) {
        Class<?> declaringClass = method.getDeclaringClass();
        Annotation[] annotations = declaringClass.getAnnotations();
        StringBuilder url = new StringBuilder();
        url.append(contextPath);
        for (Annotation annotation:annotations) {
            if(annotation instanceof RequestMapping){
                String[] value = ((RequestMapping) annotation).value();
                for (String tempUrl:value) {
                    url.append(tempUrl);
                }
            }
        }
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        for (Annotation annotation:declaredAnnotations) {
            String tempAnnotations = annotation.toString();
            if(tempAnnotations.indexOf("Mapping")>0){
                url.append(tempAnnotations.substring(tempAnnotations.indexOf("value=[")+7,tempAnnotations.lastIndexOf("],")));
            }
        }
        return url.toString().replaceAll("/+", "/");
    }


    /**
     * 验证前端传入参数,没有抛出异常
     * @param jsonObject
     * @param requiredColumns
     */
    private void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
        if (!ComUtil.isEmpty(requiredColumns)) {
            //验证字段非空
            String[] columns = requiredColumns.split(",");
            String missCol = "";
            for (String column : columns) {
                Object val = jsonObject.get(column.trim());
                if (ComUtil.isEmpty(val)) {
                    missCol += column + "  ";
                }
            }
            if (!ComUtil.isEmpty(missCol)) {
                jsonObject.clear();
                throw new ParamJsonException("缺少必填参数:"+missCol.trim());
            }
        }
    }
}
