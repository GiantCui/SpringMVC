import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * 客户端
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        /*
         * 向服务器端发送数据
         */
        System.out.println("接收端启动中。。。。。。。。。。。。。。。");

        DatagramSocket client = new DatagramSocket(8888);

        while (true) {
            byte [] bytes = new byte[1024];

            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);

            client.receive(packet);

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