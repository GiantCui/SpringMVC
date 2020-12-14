package com.cui.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * log
 * @author 
 */
@Data
public class Log implements Serializable {
    private String radarid;

    private String radarip;

    private String port;

    private String sirialnum;

    private Boolean workstate;

    private Boolean foreignmatter;

    private Boolean safetydoor;

    private Boolean radarerror;

    private String lastlog;

    private String comment;

    private String lognum;

    private Date starttime;

    private Date endtime;

    private String log;

    private static final long serialVersionUID = 1L;

    public String getRadarid() {
        return radarid;
    }

    public void setRadarid(String radarid) {
        this.radarid = radarid;
    }

    public String getRadarip() {
        return radarip;
    }

    public void setRadarip(String radarip) {
        this.radarip = radarip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSirialnum() {
        return sirialnum;
    }

    public void setSirialnum(String sirialnum) {
        this.sirialnum = sirialnum;
    }

    public Boolean getWorkstate() {
        return workstate;
    }

    public void setWorkstate(Boolean workstate) {
        this.workstate = workstate;
    }

    public Boolean getForeignmatter() {
        return foreignmatter;
    }

    public void setForeignmatter(Boolean foreignmatter) {
        this.foreignmatter = foreignmatter;
    }

    public Boolean getSafetydoor() {
        return safetydoor;
    }

    public void setSafetydoor(Boolean safetydoor) {
        this.safetydoor = safetydoor;
    }

    public Boolean getRadarerror() {
        return radarerror;
    }

    public void setRadarerror(Boolean radarerror) {
        this.radarerror = radarerror;
    }

    public String getLastlog() {
        return lastlog;
    }

    public void setLastlog(String lastlog) {
        this.lastlog = lastlog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLognum() {
        return lognum;
    }

    public void setLognum(String lognum) {
        this.lognum = lognum;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}