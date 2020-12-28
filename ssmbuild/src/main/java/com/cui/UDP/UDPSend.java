package com.cui.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPSend implements Runnable{

    private DatagramSocket server;
    private int serverPort;
    private String clientIP;
    private int clientPort;
    private SocketAddress localAdd;
    private SocketAddress destAdd;

    public UDPSend(int serverPort, String clientIP, int clientPort) throws SocketException {
        this.serverPort = serverPort;
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        try{
            this.server = new DatagramSocket(this.serverPort);
        }catch (SocketException e){
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String str = null;
            try {
                str = reader.readLine();
                byte[] bytes = str.getBytes();
                InetAddress add = InetAddress.getByName(this.clientIP);
                DatagramPacket data = new DatagramPacket(bytes, 0, bytes.length, add, this.clientPort);


                server.send(data);

                if ("exit".equals(str)) {
                    reader.close();
                    server.close();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
