package com.cui.UDP;

import com.alibaba.fastjson.JSONObject;
import com.cui.txtOP.txtFile;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.websocket.Session;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


@Controller
public class UDPReceive implements Runnable {
    private final DatagramSocket client;
    private final Session session;
    //txt 操作
    private final txtFile txtFile;
    //接受消息状态
    private Boolean state;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public UDPReceive(int clientPort, Session session) throws SocketException {
        this.client = new DatagramSocket(clientPort);
        this.session = session;
        this.state = Boolean.TRUE;
        //等待消息时长
        int MAX_TIME = 500;
        client.setSoTimeout(MAX_TIME);
        /* txt文档 */
        this.txtFile = new txtFile();
    }

    @SneakyThrows
    @ResponseBody
    public void run() {
        this.txtFile.txt_init("MsgRev.txt");
        while (this.state) {
            // System.out.println("正在监听udp消息" + this.state);
            byte [] bytes = new byte[1024];

            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);

            try {
                client.receive(packet);
            } catch (IOException e) {
                continue;
            }
            //获取发送方地址
            InetAddress address = packet.getAddress();

            byte[] data = packet.getData();

            int length = packet.getLength();

            String str = new String(data, 0, length);
            try {
                JSONObject object = JSONObject.parseObject(str);
                System.out.println("object" + object.toString());
            } catch (Exception e){
                System.out.println("非JSON格式");
            }
            this.txtFile.writeToTxt( packet.getPort() + address.toString() +":" + str);
            System.out.println(address.toString() + str );

            this.session.getBasicRemote().sendText(str);

        }
        client.close();
        this.txtFile.close_txt();
        System.out.println("结束监听udp消息");
    }


}
