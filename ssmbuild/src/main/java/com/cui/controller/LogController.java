package com.cui.controller;

import com.cui.pojo.Log;
import com.cui.pojo.Radar;
import com.cui.service.LogService;
import com.cui.service.LogServiceImpl;
import com.cui.service.RadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    @Qualifier("logServiceImpl")
    private LogServiceImpl logService;

    @RequestMapping("/allLog")
    public String list(Model model){
        List<Log> list = logService.queryAllLog();
        System.out.println("addRadar ==> " + list);
        model.addAttribute("list", list);
        return "allLog";
    }


}
