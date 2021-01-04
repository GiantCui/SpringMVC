package com.cui.controller;

import com.cui.config.ipConfig;
import com.cui.listeners.sessionAttrListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Properties;


@Controller
public class CfgController {

    @RequestMapping("/cfg/ipconfig")
    public String list(Model model) throws IOException {
        Properties properties = new Properties();
        properties.load(sessionAttrListener.class.getClassLoader().getResourceAsStream("ipconfig.properties"));
        model.addAttribute("localAddress", properties.getProperty("localAddress"));
        model.addAttribute("serverPort", properties.getProperty("serverPort"));
        model.addAttribute("targetAddress", properties.getProperty("targetAddress"));
        model.addAttribute("clientPort", properties.getProperty("clientPort"));
        System.out.println(properties.getProperty("targetAddress"));
        return "ipconfig";
    }

    @RequestMapping("/updateCfg")
    public String updateCfg(String targetIP, String receivePort, String sendPort) throws IOException {
        System.out.println("targetIP ==> " + targetIP);
        System.out.println("serverPort ==> " + receivePort);
        System.out.println("clientPort ==> " + sendPort);
        ipConfig config = new ipConfig();
        checkValue("targetAddress", targetIP, config);
        checkValue("serverPort", receivePort, config);
        checkValue("clientPort", sendPort, config);
        config.saveConfig("update");
        return "redirect:radar/allRadar";
    }

    public void checkValue(String name, String value, ipConfig config) throws IOException {
        if (value.length() > 0){
            config.setProperties(name, value);
        }
    }
}
