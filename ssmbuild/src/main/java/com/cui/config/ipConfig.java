package com.cui.config;

import com.cui.listeners.sessionAttrListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

public class ipConfig {
    private final Properties properties;


    public ipConfig() throws IOException {
        properties = new Properties();
        this.properties.load(sessionAttrListener.class.getClassLoader().getResourceAsStream("ipconfig.properties"));
    }

    public void saveConfig(String comments) throws IOException {
        FileOutputStream outputStream;
        outputStream =  new FileOutputStream(sessionAttrListener.class.getClassLoader().getResource("ipconfig.properties").getPath());
        properties.store(outputStream, comments);
        outputStream.close();
    }

    public void updateLocalIP() throws IOException {
        String old_address = properties.getProperty("localAddress");
        properties.setProperty("localAddress", InetAddress.getLocalHost().getHostAddress());
        String ipaddress = properties.getProperty("localAddress");

        System.out.println("localAddress ==> " + old_address + "==>" + ipaddress);
        saveConfig("updateLocalIP");
    }

    public void setProperties(String name, String value) throws IOException {
        properties.setProperty(name, value);
    }

    public String getProperties(String name){
        return properties.getProperty(name);
    }
}
