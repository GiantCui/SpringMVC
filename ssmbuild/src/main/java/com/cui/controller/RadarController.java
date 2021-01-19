package com.cui.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cui.pojo.Radar;
import com.cui.service.RadarService;
import com.cui.service.RadarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/radar")
public class RadarController {

    @Autowired
    @Qualifier("radarServiceImpl")
    private RadarService radarService;

    @RequestMapping("/allRadar")
    public String list(Model model) throws Exception{
        List<Radar> list = radarService.queryAllRadar();
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
    @ResponseBody
    public String toUpdateRadar(String id, Model model){
        System.out.println("queryRadar ==> " + id);
        Radar radar = radarService.queryRadarById(id);
        model.addAttribute("QRadar", radar);
        //model.addAttribute("href", href);
        return JSON.toJSONString(radar);
    }

    @RequestMapping("updateRadar")
    @ResponseBody
    public String updateRadar(@RequestBody Radar radar){

        System.out.println("updateRadar ==> " + radar);
        radarService.updateRadar(radar);
        return "success";
    }

    @RequestMapping("deleteRadar")
    public String deleteRadar(String id){
        radarService.deleteRadarById(id);
        return "redirect:/radar/allRadar";
    }

    @RequestMapping("radarView")
    public String radarView(HttpSession session, String userid, String pwd, Model model){
        if (session.getAttribute("username") == null){
            session.setAttribute("username", userid);
        }
        List<Radar> list = radarService.queryAllRadar();
        String radars = JSON.toJSONString(list);
        System.out.println("radarView ==> " + radars);
        model.addAttribute("radars", radars);
        return "radarView";
    }
}
