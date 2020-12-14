package com.cui.service;

import com.cui.dao.UsersDao;
import com.cui.pojo.Users;

public class UserServiceImpl implements UserService{
    UsersDao user;

    public void setUser(UsersDao user) {
        this.user = user;
    }

    public UserServiceImpl(UsersDao user) {
        this.user = user;
    }

    public Users queryByUserId(String userid) {
        return user.queryByUserId(userid);
    }
}
