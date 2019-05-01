package com.jxbig.sharp.eurekaclient.manager.rpc;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@ServerEndpoint("/websocket")
@Component
public class WebSocketRpc {

    private static final ThreadLocal<Session> sessions = new ThreadLocal<>();
    private static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList set = new CopyOnWriteArrayList<Session>();

    private static AtomicLong count = new AtomicLong(0);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session){
        try {
            sessions.set(session);
            sessionMap.put(session.getId(), session);
            count.addAndGet(1);
            sendMessage();
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        System.out.println(count.get());
        count.getAndDecrement();
        System.out.println("有一连接关闭！当前在线人数为： " + count.get());
        try {
            sendMessage1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.println("来自客户端的消息:" + message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage() throws IOException {
        sessionMap.entrySet().forEach(e -> {
            try {
                if (e.getValue().isOpen()) {
                    e.getValue().getBasicRemote().sendText("新用户{"+ sessions.get().getId() +"}添加进来了...." + count.get());
                    System.out.println("发送的sessionId：" + e.getKey());
                } else {
                    System.out.println("此session已经失效，sessionId: " + e.getKey());
                    sessionMap.remove(e.getKey());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void sendMessage1() throws IOException {
        Vector<Session> sessions = new Vector<>();
        AtomicReference<Session> invideSession = new AtomicReference<>();
        sessionMap.entrySet().forEach(e -> {
            if (e.getValue().isOpen()) {
                sessions.add(e.getValue());
            } else {
                invideSession.set(e.getValue());
                sessionMap.remove(e.getKey());
            }
        });
        sessions.forEach(v -> {
            try {
                v.getBasicRemote().sendText("用户{"+ invideSession.get().getId() +"}下线了...." + count.get());
                System.out.println("发送的sessionId：" + v.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
