package com.cui.WS;


import com.cui.UDP.UDPReceive;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class WSAnnotation {
    /**
     * @onlineCount :静态变量，用来记录当前在线连接数。
     * @webSocketSet :存放每个客户端对应的MyWebSocket对象。
     * @udpRcv :与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @clientPort : 接受端口号
     */
    private static int onlineCount = 0;
    private static final CopyOnWriteArraySet<WSAnnotation> webSocketSet = new CopyOnWriteArraySet<WSAnnotation>();
    private Session session;
    private UDPReceive udpRcv;
    private static int clientPort = 8888;
    private static final String clientIP = "192.168.1.96";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws SocketException {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        udpRcv = new UDPReceive(clientPort, session);
        udpRcv.setState(Boolean.TRUE);
        new Thread(udpRcv).start();
        System.out.println("开始监听, 端口号：\t" + clientPort);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws IOException {
        udpRcv.setState(Boolean.FALSE);
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for(WSAnnotation item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exitUDPRcv() throws IOException {
        DatagramSocket server = new DatagramSocket(1234);
        String str = "exit";
        byte []bytes = str.getBytes();
        InetAddress add = InetAddress.getByName(clientIP);
        DatagramPacket data = new DatagramPacket(bytes, 0, bytes.length, add, clientPort);
        server.send(data);
        server.close();
    }

    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    public static int getOnlineCount() {
        return onlineCount;
    }

    public static void setOnlineCount(int onlineCount) {
        WSAnnotation.onlineCount = onlineCount;
    }

    public static void setClientPort(int clientPort) {
        WSAnnotation.clientPort = clientPort;
    }
}
