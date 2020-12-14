package com.cui.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * users
 * @author cui_a
 */
@Data
public class Users implements Serializable {
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 用户名
     */
    private String userid;

    /**
     * 密码
     */
    private String password;

    private static final long serialVersionUID = 1L;
}