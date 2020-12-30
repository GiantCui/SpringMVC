import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class configText {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties pro = new Properties();
        // 存储
        pro.setProperty("driver", "oracle.jdbc.dirver.OracleDriver");
        pro.setProperty("url", "jdbc:oracle：thin：@localhost:1521:orcl");
        pro.setProperty("user", "scott");
        pro.setProperty("pwd", "tiger");
        // 获取
        // String url1=pro.getProperty("url1", "test");//存在获取给定值，不存在获取默认值
        // System.out.println(url1);

        // 存储到e:others绝对路径 盘符：
        // pro.store(new FileOutputStream("e:/others/db.properties"), "db配置");
        // pro.storeToXML(new FileOutputStream("e:/others/db.xml"), "db配置");
        // 使用相对路径 当前工程
        pro.store(new FileOutputStream("db.properties"), "db配置");
    }

}
