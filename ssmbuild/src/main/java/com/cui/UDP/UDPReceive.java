package com.cui.UDP;

import com.cui.WS.WSAnnotation;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class UDPReceive implements Runnable {
    private final DatagramSocket client;
    private final Session session;
    private Boolean state;
    private final int MAX_TIME = 3000;

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
        client.setSoTimeout(MAX_TIME);
    }

    @SneakyThrows
    @ResponseBody
    public void run() {
        while (this.state) {
            System.out.println("正在监听udp消息" + this.state);
            byte [] bytes = new byte[1024];

            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);

            try {
                client.receive(packet);
            } catch (IOException e) {
                continue;
            }
            InetAddress add = packet.getAddress();
            byte[] data = packet.getData();

            int length = packet.getLength();

            String str = new String(data, 0, length);

            System.out.println(add.toString() + str );

            this.session.getBasicRemote().sendText(add.getHostAddress().toString() + ":" + str);

            if ("exit".equals(str)){
                client.close();
                break;
            }
        }
        client.close();
        System.out.println("结束监听udp消息");
    }

    public void closeClient() {
        this.client.close();
    }
}
