package com.cui.controller;


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
    public String toAddRadar(){
        return "addRadar";
    }

    @RequestMapping("/addRadar")
    public String addRadar(Radar radar){
        System.out.println("addRadar ==> " + radar);

        radarService.insertRadar(radar);


        return "redirect:/radar/allRadar";
    }

    @RequestMapping("/toUpdate")
    public String toUpdateRadar(String id, Model model){
        System.out.println("queryRadar ==> " + id);
        Radar radar = radarService.queryRadarById(id);
        model.addAttribute("QRadar", radar);
        return "updateRadar";
    }

    @RequestMapping("updateRadar")
    public String updateRadar(Radar radar){
        System.out.println("updateRadar ==> " + radar);
        radarService.updateRadar(radar);
        return "redirect:/radar/allRadar";
    }

    @RequestMapping("deleteRadar")
    public String deleteRadar(String id){
        radarService.deleteRadarById(id);
        return "redirect:/radar/allRadar";
    }
}