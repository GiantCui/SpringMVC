package com.cui.listeners;

import com.cui.UDP.UDPReceive;
import com.cui.UDP.UDPSend;
import com.cui.config.ipConfig;
import lombok.SneakyThrows;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.*;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Properties;

public class sessionAttrListener implements HttpSessionAttributeListener {
    @SneakyThrows
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        String str = (String) session.getAttribute("username");
        System.out.println("用户登录 ==> " + str);
        ipInit();

    }

    private void ipInit() throws IOException {
        //更新本机IP地址
        ipConfig config = new ipConfig();
        config.updateLocalIP();
    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        String str = (String) session.getAttribute("'username");
        System.out.println("用户注销 ==> " + str);
    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
