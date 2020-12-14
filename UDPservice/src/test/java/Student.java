import java.net.SocketException;

public class Student {
    public static void main (String[] args) throws SocketException {
        System.out.println("学生端启动中。。。。。。。。。。。。。");
        new Thread(new UDPSend(6666, "192.168.1.162", 8888)).start();
        new Thread(new UDPReceive(7777)).start();
    }
}
