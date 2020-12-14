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
}
