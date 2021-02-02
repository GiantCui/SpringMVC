package com.cui.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * log
 * @author CuiKJ
 */
@Data
public class Log implements Serializable {
    private long logNum;
    private String radarID;
    private String log;
    private String time;
    private boolean progress;

    public long getLogNum() {
        return logNum;
    }

    public void setLogNum(long logNum) {
        this.logNum = logNum;
    }

    public String getRadarID() {
        return radarID;
    }

    public void setRadarID(String radarID) {
        this.radarID = radarID;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}