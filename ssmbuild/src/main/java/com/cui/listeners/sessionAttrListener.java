package com.cui.listeners;

import com.cui.UDP.UDPReceive;
import com.cui.UDP.UDPSend;
import lombok.SneakyThrows;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class sessionAttrListener implements HttpSessionAttributeListener {
    @SneakyThrows
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        String str = (String) session.getAttribute("'username");
        System.out.println("用户登录 ==> " + str);
        System.out.println("开始监听");
        // new Thread(new UDPReceive(8888)).start();
        // new Thread(new UDPSend(9999, "192.168.1.162", 7777)).start();
    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        String str = (String) session.getAttribute("'username");
        System.out.println("用户注销 ==> " + str);
    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
