package com.cui.controller;


import com.alibaba.fastjson.JSON;
import com.cui.pojo.Radar;
import com.cui.service.RadarService;
import com.cui.service.RadarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/radar")
public class RadarController {

    @Autowired
    @Qualifier("radarServiceImpl")
    private RadarService radarService;

    @RequestMapping("/allRadar")
    public String list(HttpSession session, String userid, String pwd, Model model) throws Exception{
        System.out.println("User, pwd==>" + userid + pwd);
        List<Radar> list = radarService.queryAllRadar();
        session.setAttribute("username", userid);
        model.addAttribute("list", list);
        return "allRadar";
    }

    @RequestMapping("/toAddRadar")
    public String toAddRadar(String href, Model model){
        System.out.println("toAddRadar ==> " + href);
        model.addAttribute("href", href);
        return "addRadar";
    }

    @RequestMapping("/addRadar")
    public String addRadar(Radar radar, String href){
        System.out.println("addRadar ==> " + radar);

        radarService.insertRadar(radar);


        return "redirect:" + href;
    }

    @RequestMapping("/toUpdate")
    public String toUpdateRadar(String id, String href, Model model){
        System.out.println("queryRadar ==> " + id);
        Radar radar = radarService.queryRadarById(id);
        model.addAttribute("QRadar", radar);
        model.addAttribute("href", href);
        return "updateRadar";
    }

    @RequestMapping("updateRadar")
    public String updateRadar(Radar radar, String href){
        System.out.println("updateRadar ==> " + radar);
        System.out.println("updateRadar ==> href:" + href);
        radarService.updateRadar(radar);
        return "redirect:" + href;
    }

    @RequestMapping("deleteRadar")
    public String deleteRadar(String id){
        radarService.deleteRadarById(id);
        return "redirect:/radar/allRadar";
    }

    @RequestMapping("radarView")
    public String radarView(Model model){
        List<Radar> list = radarService.queryAllRadar();
        String radars = JSON.toJSONString(list);
        System.out.println("radarView ==> " + radars);
        model.addAttribute("radars", radars);
        return "radarView";
    }
}
