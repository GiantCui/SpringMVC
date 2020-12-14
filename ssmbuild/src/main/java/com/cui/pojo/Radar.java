package com.cui.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * radar
 * @author
 */
@Data
public class Radar implements Serializable {
    /**
     * 雷达id
     */
    private String radarid;

    /**
     * 雷达ip
     */
    private String radarip;

    /**
     * 端口号
     */
    private String port;

    /**
     * 雷达编号
     */
    private String sirialnum;

    /**
     * 工作状态
     */
    private Boolean workstate;

    /**
     * 异物检测状态
     */
    private Boolean foreignmatter;

    /**
     * 屏蔽门状态
     */
    private Boolean safetydoor;

    /**
     * 雷达异常
     */
    private Boolean radarerror;

    /**
     * 最近日志编号
     */
    private String lastlog;

    /**
     * 备注
     */
    private String comment;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}