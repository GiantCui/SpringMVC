package com.cui.controller;

import com.cui.listeners.sessionAttrListener;
import com.cui.pojo.Log;
import com.cui.service.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Properties;


@Controller
@RequestMapping("/cfg")
public class CfgController {
    @Autowired
    @Qualifier("logServiceImpl")
    private LogServiceImpl logService;

    @RequestMapping("/ipconfig")
    public String list(Model model) throws IOException {
        Properties properties = new Properties();
        properties.load(sessionAttrListener.class.getClassLoader().getResourceAsStream("ipconfig.properties"));
        model.addAttribute("ipaddress", properties.getProperty("ipaddress"));
        return "ipconfig";
    }
}
