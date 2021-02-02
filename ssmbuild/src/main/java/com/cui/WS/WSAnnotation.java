package com.cui.WS;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cui.UDP.UDPReceive;
import com.cui.controller.udpController;
import com.cui.pojo.Radar;

import java.io.IOException;
import java.net.*;
import java.util.List;
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
    private static final int onlineCount = 0;
    private static final CopyOnWriteArraySet<WSAnnotation> webSocketSet = new CopyOnWriteArraySet<WSAnnotation>();
    private UDPReceive udpRcv;
    private udpController udpCtr;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws SocketException {
        webSocketSet.add(this);     //加入set中
        int clientPort = 8888;
        udpRcv = new UDPReceive(clientPort, session);
        udpRcv.setState(Boolean.TRUE);
        udpCtr = new udpController();
        Thread thread = new Thread(udpRcv);
        thread.start();
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
    public void onMessage(String message, Session session) throws Exception{
        System.out.println("来自客户端的消息:" + message);
        try{
            // 转换JSON数据
            JSONObject data = JSON.parseObject(message);
            // 获取命令名
            Cmd cmd = Cmd.valueOf(data.getString("cmd").toUpperCase());
            System.out.println("收到命令" + cmd);
            // 判断命令
            switch (cmd){
                case INIT_R: init_R(data); break;
                case STATE_CHG: break;
                case FOREIGNMATTER_T:
                case FOREIGNMATTER_F:
                    foreignMatter(cmd.toString(), data.getString("serialnum")); break;
                case ALARMELIMINATION: alarmElimination(data); break;
                case BYPASSALL: byPassAll(); break;
            }
            // 解析JSON字符串
            //List<IPC> IPCS = JSON.parseArray(data.getJSONArray("ipc").toJSONString(), IPC.class);
        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("收到命令非JSON格式");
        }
    }

    private void init_R(JSONObject data){
        // 解析JSON字符串
        List<Radar> radars = JSON.parseArray(data.getJSONArray("RADAR").toJSONString(), Radar.class);
        for (Radar radar : radars) {
            System.out.println(radar);
            udpCtr.initRadar(radar);
        }
    }

    private void foreignMatter(String command, String serialnum){
        udpCtr.updateRadar(command, serialnum);
    }

    private void alarmElimination(JSONObject data){
        List<String> lists = JSON.parseArray(data.getJSONArray("warn_num").toJSONString(), String.class);
        for (String ls : lists){
            foreignMatter(Cmd.FOREIGNMATTER_F.toString(), ls);
        }
    }

    private void byPassAll(){
        udpCtr.byPassAll();
    }
    private enum Cmd{
        INIT_R, STATE_CHG, FOREIGNMATTER_T, FOREIGNMATTER_F, ALARMELIMINATION,
        BYPASSALL
    }
}
