package com.liugh.util;

import com.alibaba.fastjson.JSON;
import com.liugh.base.BusinessException;
import com.liugh.base.Constant;
import com.liugh.base.SmsSendRequest;
import com.liugh.base.SmsSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author liugh
 */
public class SmsSendUtil {

    private static final Logger logger = LoggerFactory.getLogger(SmsSendUtil.class);

    private static final String CHARSET = "utf-8";

    /**
     * 短信平台请求地址
     */
    private static final String URL = "http://smssh1.253.com/msg/send/json";

    /**
     * 用户平台API账号(非登录账号,示例:N1234567)
     */
    private static final String ACCOUNT = "*****";

    /**
     * 用户平台API密码(非登录密码)
     */
    private static final String PASSWORD = "*****";


    private static final String REPORT = "true";

    private static final String SIGNATURE ="【*****】";

    /**
     * 调用第三方平台，下发短信
     * @param receiveUser
     * @param message
     * @return
     */
    public static SmsSendResponse sendMessage(String receiveUser, String message) throws BusinessException {
        String str = SIGNATURE + message;
        SmsSendRequest smsSingleRequest = new SmsSendRequest(ACCOUNT, PASSWORD, str, receiveUser,REPORT);
        String requestJson = JSON.toJSONString(smsSingleRequest);
        logger.info("before request string is: " + requestJson);
        String response = sendSmsByPost(URL, requestJson);
        logger.info("response after request result is :" + response);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        logger.info(smsSingleResponse.toString());
        return smsSingleResponse;
    }

    /**
     *
     * @author tianyh
     * @Description
     * @param path
     * @param postContent
     * @return String
     * @throws
     */
    private static String sendSmsByPost(String path, String postContent) throws BusinessException {
        java.net.URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", CHARSET);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();
            OutputStream os=httpURLConnection.getOutputStream();
            os.write(postContent.getBytes(CHARSET));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), CHARSET));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            } else {
                throw new BusinessException("短信下发失败");
            }
        } catch (Exception e) {
            throw new BusinessException("短信下发失败");
        }
    }

    public enum SMSType{
        AUTH,

        REG,

        FINDPASSWORD,


        MODIFYINFO;

        /**
         * toString
         *
         * @return String
         */
        @Override
        public String toString(){
            String sMSType = "";
            switch (this){
                case AUTH:
                    sMSType = "登陆验证";
                    break;
                case REG:
                    sMSType = "注册账号";
                    break;
                case FINDPASSWORD:
                    sMSType = "修改密码";
                    break;
                case MODIFYINFO:
                    sMSType = "修改账号";
                    break;
                default:
                    sMSType = "";
            }
            return sMSType;
        }

        public static Integer getType(String str){
            Integer sMSType;
            switch (str){
                case "AUTH":
                    sMSType = 1;
                    break;
                case "REG":
                    sMSType = 2;
                    break;
                case "FINDPASSWORD":
                    sMSType = 3;
                    break;
                case "MODIFYINFO":
                    sMSType = 4;
                    break;
                default:
                    sMSType = 0;
            }
            return sMSType;
        }

        public static SMSType getSMSType(String str){
            SMSType sMSType = null;
            for (SMSType ut : SMSType.values()){
                if (ut.name().equals(str)){
                    sMSType = ut;
                    break;
                }
            }
            return sMSType;
        }
    }

    public static boolean isCaptchaPassTime(Long sendTime){
        long now = System.currentTimeMillis();
        if(now - sendTime > Constant.PASS_TIME){
           return true;
        }
        return false;
    }

    public static void main(String[] args) throws BusinessException{
         sendMessage("17765071662", "我试试请求，看一看结果");
    }
}
