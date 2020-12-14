import com.cui.pojo.Radar;
import com.cui.service.RadarService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RadarService radarService = (RadarService) context.getBean("RadarServiceImpl");
        for (Radar radar : radarService.queryAllRadar()){
            System.out.println(radar);
        }
    }
}
