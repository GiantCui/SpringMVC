package com.cui.service;

import com.cui.dao.LogDao;
import com.cui.pojo.Log;

import java.util.List;

public class LogServiceImpl implements LogService{
    LogDao Log;

    public void setLog(LogDao log) {
        Log = log;
    }

    public List<Log> queryAllLog() {
        return Log.queryAllLog();
    }

    public int getSize(){ return Log.getSize();}

    public List<Log> getPage(int start, int end){ return Log.getPage(start, end);}
}
