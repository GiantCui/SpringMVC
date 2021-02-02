package com.cui.dao;

import com.cui.pojo.Log;

import java.util.List;

public interface LogDao {

    List<Log> queryAllLog();

    int getSize();

    List<Log> getPage(int start, int end);
}