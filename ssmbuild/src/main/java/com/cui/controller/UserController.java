package com.cui.controller;

import com.cui.pojo.Users;
import com.cui.service.RadarService;
import com.cui.service.UserService;
import com.cui.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUser(String userid){
        System.out.println("userid  ==> " + userid);
        Users user = userService.queryByUserId(userid);
        if (user != null){
            return "success";
        }
        else {
            return "false";
        }
    }

    @RequestMapping("/checkPwd")
    @ResponseBody
    public String checkPwd(String userid, String pwd){
        System.out.println("userPwd  ==> " + userid);
        Users user = userService.queryByUserId(userid);
        if (user != null && pwd.equals(user.getPassword())){
            return "success";
        }
        else {
            return "false";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession, String href){
        String str = (String) httpSession.getAttribute("username");
        System.out.println("注销用户 ==> " + str);
        httpSession.removeAttribute("username");
        return "index";
    }
}
