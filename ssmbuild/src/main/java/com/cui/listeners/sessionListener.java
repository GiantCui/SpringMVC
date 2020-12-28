package com.cui.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class sessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("创建监听对象 ==> ");
        System.out.println(httpSessionEvent.getSession().getAttribute("username"));

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
