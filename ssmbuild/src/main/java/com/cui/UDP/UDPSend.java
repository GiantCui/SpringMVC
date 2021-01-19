package com.cui.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPSend implements Runnable{

    private DatagramSocket server;
    private final String clientIP;
    private final int clientPort;


    public UDPSend(String clientIP, int clientPort) throws SocketException {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        try{
            this.server = new DatagramSocket(8887);
        }catch (SocketException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {
        byte[] bytes = message.getBytes();
        InetAddress add = InetAddress.getByName(clientIP);
        DatagramPacket data = new DatagramPacket(bytes, 0, bytes.length, add, this.clientPort);
        server.send(data);
        server.close();
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
