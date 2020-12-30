package com.cui.listeners;

import com.cui.UDP.UDPReceive;
import com.cui.UDP.UDPSend;
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
        Properties properties = new Properties();
        properties.load(sessionAttrListener.class.getClassLoader().getResourceAsStream("ipconfig.properties"));
        //InputStream in = sessionAttrListener.class.getClassLoader().getResourceAsStream("ipconfig.properties");

        FileOutputStream out =  new FileOutputStream(sessionAttrListener.class.getClassLoader().getResource("ipconfig.properties").getPath());

        String old_address = properties.getProperty("ipaddress");
        properties.setProperty("ipaddress", InetAddress.getLocalHost().getHostAddress());
        String ipaddress = properties.getProperty("ipaddress");

        System.out.println("ipaddress ==> " + old_address + "==>" + ipaddress);
        properties.store(out, "updata");
        out.close();
    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        String str = (String) session.getAttribute("'username");
        System.out.println("用户注销 ==> " + str);
    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
