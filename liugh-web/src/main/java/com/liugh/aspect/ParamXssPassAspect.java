package com.liugh.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 防止xss攻击切面
 * Created by liugh on 2018/10/12.
 */
public class ParamXssPassAspect extends  AbstractAspectManager{

    public ParamXssPassAspect(AspectApi aspectApi){
        super(aspectApi);
    }


    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp,method);
        execute(pjp,method);
        return null;
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method)throws Throwable{
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
                    sb.append("《");
                    break;
                case '<':
                    //全角小于号
                    sb.append("》");
                    break;
                case '\'':
                    //全角单引号
                    sb.append('‘');
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
