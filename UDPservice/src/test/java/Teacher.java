import java.net.SocketException;

public class Teacher {
    public static void main(String[] args) throws SocketException {
        System.out.println("老师端启动中。。。。。。。。。。。。。");
        new Thread(new UDPReceive(8888)).start();
        new Thread(new UDPSend(9999, "192.168.1.162", 7777)).start();
    }
}
