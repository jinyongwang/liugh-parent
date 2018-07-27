package com.liugh.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liugh
 * @since on 2018/7/11.
 */
@ServerEndpoint(value = "/api/websocket/{mobile}")
@Component
public class MyWebSocketService {

    private static Logger logger = LoggerFactory.getLogger(MyWebSocketService.class);

    /**
     *  用来记录当前在线连接数
     */
    private static   AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static Map<String, MyWebSocketService> clients = new ConcurrentHashMap<String, MyWebSocketService>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private String mobile;

    /**
     * 在客户初次连接时触发，
     * 这里会为客户端创建一个session，这个session并不是我们所熟悉的httpsession
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("mobile") String mobile, Session session) {
        this.session = session;
        this.mobile = mobile;
        //在线数加1
        addOnlineCount();
        clients.put(mobile,this);
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
//        try {
//            sendMessage("当前人数为："+getOnlineCount());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 在客户端与服务器端断开连接时触发。
     */
    @OnClose
    public void onClose() {
        //从set中删除
        clients.remove(mobile);
        //在线数减1
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 消息内容
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject requestJson =JSONObject.parseObject(message);
        if (!requestJson.getString("to").equals("all")){
            sendMessageTo(requestJson.toJSONString(), requestJson.get("to").toString());
        }else{
            sendMessageAll(requestJson.toJSONString());
        }
    }

    public static void sendMessageTo(String message, String To) throws IOException {
        Set<Map.Entry<String, MyWebSocketService>> entries = clients.entrySet();
        for (Map.Entry<String, MyWebSocketService> item : entries) {
            if (item.getKey().equals(To) ) {
                item.getValue().session.getAsyncRemote().sendText(message);
            }
        }
    }

    public static void sendMessageAll(String message) throws IOException {
        Set<Map.Entry<String, MyWebSocketService>> entries = clients.entrySet();
        for (Map.Entry<String, MyWebSocketService> item : entries) {
            item.getValue().session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 发生错误时调用此方法
     * @param session
     * @param error
     */
    @OnError
    public  void onError(Session session, Throwable error) {
        logger.error(error.toString()+"发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息到页面
     * @param message 消息内容
     * @throws IOException
     */
    public  void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 得到当前联接人数
     * @return
     */
    public static   synchronized AtomicInteger getOnlineCount() {
        return onlineCount;
    }

    /**
     * 增加联接人数
     */
    public static   synchronized void addOnlineCount() {
        onlineCount.incrementAndGet();
    }

    /**
     * 减少联接人数
     */
    public  static  synchronized void subOnlineCount() {
        onlineCount.decrementAndGet();
    }
}
