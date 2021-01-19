package com.cui.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class IPC {
    /**
     * 工控机IP地址
     */
    @JSONField(name = "id")

    private String id;
    /**
     * 工控机端口号
     */
    @JSONField(name = "port")
    private String port;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "IPC{" +
                "id='" + id + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
