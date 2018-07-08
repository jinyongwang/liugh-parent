package com.liugh.aspect;

import com.alibaba.fastjson.JSONObject;
import com.liugh.annotation.Log;
import com.liugh.annotation.ParamXssPass;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.BaseResult;
import com.liugh.base.PageResult;
import com.liugh.entity.User;
import com.liugh.util.ComUtil;
import com.liugh.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 切面:防止xss攻击 记录log  参数验证
 * @author liugh
 * @since 2018-05-03
 */
@Aspect
@Configuration
public class ParameterCheckAspect {

    /**
     * 防止XSS攻击
     */
    @Pointcut("execution(* com.liugh.controller..*(..))  ")
    public void preventXSS() {
    }
    /**
     * Log注解
     */
    @Pointcut("@annotation(com.liugh.annotation.Log)")
    public void recordLog(){
    }


    @Around(value = "preventXSS() || recordLog()")
    public Object validationPoint(ProceedingJoinPoint pjp)throws Throwable{
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
     * 设置返回参数中password隐藏
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around( "execution(* com.liugh.controller..*(..) )" )
    public Object returnValueHandle ( ProceedingJoinPoint joinPoint ) throws Throwable {
        Object returnValue = joinPoint.proceed();
        if(returnValue instanceof BaseResult){
            BaseResult caseResult = (BaseResult) returnValue;
            Object data = caseResult.getData();
            if(data instanceof PageResult){
                PageResult pageResult = (PageResult) data;
                List list = pageResult.getList();
                doPasswd2NullByList(list);
                pageResult.setList(list);
            }
            if(data instanceof User){
                User user = (User) data;
                user.setPassWord(null);
                caseResult.setData(user);
            }
            if(data instanceof Map){
                Map<String,Object> map = (Map) data;
                doPasswd2NullByMap(map);
                caseResult.setData(map);
            }
            if(data instanceof List){
                List list = (List) data;
                doPasswd2NullByList(list);
                caseResult.setData(list);
            }
        }
        return  returnValue;
    }

    private void doPasswd2NullByList(List list) {
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i) instanceof User){
                User user = (User) list.get(i);
                user.setPassWord(null);
                list.set(i,user);
            }
            if(list.get(i) instanceof Map){
                Map<String,Object> map = (Map) list.get(i);
                doPasswd2NullByMap(map);
                list.set(i,map);
            }
        }
    }

    private void doPasswd2NullByMap(Map<String, Object> map) {
        for (String key: map.keySet()) {
            Object obj = map.get(key);
            if(obj instanceof User){
                User user = (User) obj;
                user.setPassWord(null);
                map.put(key,user);
            }
        }
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
