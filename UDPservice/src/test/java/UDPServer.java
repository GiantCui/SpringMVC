import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/*
 * 服务器端，实现基于UDP的用户登陆
 */
public class UDPServer {
    public static void main(String[] args) throws IOException, SocketException {
        System.out.println("发送端启动中。。。。。。。。。。。。。。。");

        DatagramSocket server = new DatagramSocket(6666);
        while (true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String str = reader.readLine();

            byte [] bytes = str.getBytes();

            DatagramPacket data = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 8888));


            server.send(data);

            if("exit".equals(str)){
                reader.close();
                server.close();
                break;
            }
        }

    }
}