package com.liugh.aspect;

import com.alibaba.fastjson.JSONObject;
import com.liugh.annotation.Log;
import com.liugh.annotation.ParamXssPass;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.Constant;
import com.liugh.util.ComUtil;
import com.liugh.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 切面:防止xss攻击 记录log  参数验证
 * @author liugh
 * @since 2018-05-03
 */
@Aspect
@Configuration
public class ParameterCheckAspect {

    @Pointcut("execution(* com.liugh.controller..*(..))  ")
    public void aspect() {
    }


    @Around(value = "aspect()")
    public Object validationPoint(ProceedingJoinPoint pjp)throws Throwable{
        Constant.isPass=false;
        Method method = currentMethod(pjp,pjp.getSignature().getName());
        boolean  isLogEmpty  = Objects.isNull(method.getAnnotation( Log.class ));
        boolean  isParamXssPassEmpty  = Objects.isNull(method.getAnnotation( ParamXssPass.class ));
        Object[] args = pjp.getArgs();
        if(isParamXssPassEmpty){
            args = handlerRequstParam(pjp);
        }
        boolean isValidationParamEmpty = ComUtil.isEmpty(StringUtil.getMethodAnnotationOne(method, ValidationParam.class.getSimpleName()));
        if (!isLogEmpty && isValidationParamEmpty) {
            AspectHandler aspectHandler = new RecordLogOperate();
            return   aspectHandler.doAspectHandler(pjp,args,method,false);
        }
        if(!isValidationParamEmpty && !isLogEmpty){
            AspectHandler aspectHandler = new RecordLogOperate();
            return   aspectHandler.doAspectHandler(pjp,args,method,true);
        }
        if(!isValidationParamEmpty && isLogEmpty){
            AspectHandler aspectHandler = new ValidationParamOperate();
            aspectHandler.doAspectHandler(pjp,args,method,false);
        }
        return  pjp.proceed(args);
    }

    /**
     * 获取目标类的所有方法，找到当前要执行的方法
     */
    private Method currentMethod ( ProceedingJoinPoint joinPoint , String methodName ) {
        Method[] methods      = joinPoint.getTarget().getClass().getMethods();
        Method   resultMethod = null;
        for ( Method method : methods ) {
            if ( method.getName().equals( methodName ) ) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

    private Object[] handlerRequstParam(ProceedingJoinPoint pjp){
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof JSONObject){
                args[i]=JSONObject.parseObject(xssEncode(args[i].toString()));
            }else if(args[i] instanceof String){
                args[i]=xssEncode(args[i].toString());
            }else {
                continue;
            }
        }
        return args;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    private  String xssEncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    //全角大于号
                    sb.append("&gt;");
                    break;
                case '<':
                    //全角小于号
                    sb.append("&lt;");
                    break;
                case '\'':
                    //全角单引号
                    sb.append('‘');
                    break;
                case '&':
                    //全角
                    sb.append('＆');
                    break;
                case '\\':
                    //全角斜线
                    sb.append('＼');
                    break;
                case '#':
                    //全角井号
                    sb.append('＃');
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

}
