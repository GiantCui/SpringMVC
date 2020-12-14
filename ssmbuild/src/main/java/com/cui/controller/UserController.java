package com.cui.controller;

import com.cui.pojo.Users;
import com.cui.service.RadarService;
import com.cui.service.UserService;
import com.cui.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @RequestMapping("/checkUser")
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
}
