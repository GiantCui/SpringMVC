package com.cui.controller;

import com.cui.pojo.Radar;
import com.cui.service.RadarService;
import com.cui.service.RadarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class udpController {
    @Autowired
    @Qualifier("radarServiceImpl")
    private RadarService radarService;

    private static udpController controller;

    @PostConstruct
    private void init(){
        controller = this;
        controller.radarService = this.radarService;
    }

    public void initRadar(Radar radar){
        System.out.println("udpController" + radar);

        controller.radarService.initRadar(radar);
    }

    public void updateRadar(String command, String serialnum){
        Radar radar = controller.radarService.queryRadarBySirialnum(serialnum);
        System.out.println("udpController ==> " + command + "," + serialnum);
        System.out.println(radar.getForeignmatter());
        Cmd cmd = Cmd.valueOf(command);
        boolean update = false;
        switch (cmd){
            case FOREIGNMATTER_T: if (radar.getForeignmatter().equals("0")) {
                radar.setForeignmatter("1");
                update = true;
            }break;
            case FOREIGNMATTER_F: if (radar.getForeignmatter().equals("1")){
                radar.setForeignmatter("0");
                update = true;
            }break;
        }
        if (update){
            controller.radarService.updateRadar(radar);
        }
    }

    public void byPassAll(){
        List<Radar> radars= controller.radarService.queryAllRadar();
        for (Radar radar : radars){
            radar.setWorkstate("0");
            controller.radarService.updateRadar(radar);
        }
    }

    private enum Cmd{
        INIT_R, STATE_CHG, FOREIGNMATTER_T, FOREIGNMATTER_F
    }
}
