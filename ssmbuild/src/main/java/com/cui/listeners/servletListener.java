package com.cui.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class servletListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("监听网页");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("网页销毁");
    }
}
