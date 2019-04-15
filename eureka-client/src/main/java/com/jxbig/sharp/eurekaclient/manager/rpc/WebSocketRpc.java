package com.jxbig.sharp.eurekaclient.manager.rpc;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket")
@Component
public class WebSocketRpc {

    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session)
    {
        System.out.println(this.hashCode());
        this.session = session;
        try
        {
            sendMessage("新用户添加进来了....");
        }
        catch (IOException e)
        {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose()
    {
        System.out.println("有一连接关闭！当前在线人数为");
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
    public void onError(Session session, Throwable error)
    {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException
    {
        this.session.getBasicRemote().sendText(message);
    }
}
