import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceive implements Runnable{


    private DatagramSocket client;
    private int clientPort;

    public UDPReceive(int clientPort) throws SocketException {
        this.clientPort = clientPort;
        this.client = new DatagramSocket(this.clientPort);
    }
    public void run() {
        while (true) {
            byte [] bytes = new byte[1024];

            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);

            try {
                client.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = packet.getData();

            int length = packet.getLength();

            String str = new String(data, 0, length);

            System.out.println(str);


            if ("exit".equals(str)){
                client.close();
                break;
            }
        }
    }
}
