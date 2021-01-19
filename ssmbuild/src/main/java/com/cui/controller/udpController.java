package com.cui.controller;

import com.cui.pojo.Radar;
import com.cui.service.RadarService;
import com.cui.service.RadarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Component
public class udpController {
    @Autowired
    @Qualifier("radarServiceImpl")
    private RadarService radarService;

    private static udpController controller;
    public void initRadar(Radar radar){
        System.out.println("udpController" + radar);

        controller.radarService.initRadar(radar);
    }

    @PostConstruct
    private void init(){
        controller = this;
        controller.radarService = this.radarService;
    }
}
