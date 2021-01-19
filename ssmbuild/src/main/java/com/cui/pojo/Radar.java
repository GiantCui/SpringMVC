package com.cui.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * radar
 * @author cui
 */
@Data
public class Radar implements Serializable {
    /**
     * 雷达id
     */
    @JSONField(name = "radarID")
    private String radarid;

    /**
     * 雷达ip
     */
    @JSONField(name = "radarIP")
    private String radarip;

    /**
     * 端口号
     */
    @JSONField(name = "port")
    private String port;

    /**
     * 雷达编号
     */
    @JSONField(name = "sirialnum")
    private String sirialnum;

    /**
     * 工作状态
     */
    @JSONField(name = "workstate")
    private String workstate;

    /**
     * 异物检测状态
     */
    @JSONField(name = "foreignmatter")
    private String foreignmatter;

    /**
     * 屏蔽门工作状态
     */
    @JSONField(name = "safetydoor")
    private String safetydoor;

    /**
     * 屏蔽门开关状态
     */
    @JSONField(name = "doorstate")
    private String doorstate;

    /**
     * 雷达异常
     */
    @JSONField(name = "radarerror")
    private String radarerror;

    /**
     * 最近日志编号
     */
    @JSONField(name = "lastlog")
    private String lastlog;

    /**
     * 备注
     */
    @JSONField(name = "comment")
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

    public String getWorkstate() {
        return workstate;
    }

    public void setWorkstate(String workstate) {
        this.workstate = workstate;
    }

    public String getForeignmatter() {
        return foreignmatter;
    }

    public void setForeignmatter(String foreignmatter) {
        this.foreignmatter = foreignmatter;
    }

    public String getSafetydoor() {
        return safetydoor;
    }

    public void setSafetydoor(String safetydoor) {
        this.safetydoor = safetydoor;
    }

    public String getDoorstate() {
        return doorstate;
    }

    public void setDoorstate(String doorstate) {
        this.doorstate = doorstate;
    }

    public String getRadarerror() {
        return radarerror;
    }

    public void setRadarerror(String radarerror) {
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



    @Override
    public String toString() {
        return "Radar{" +
                "radarid='" + radarid + '\'' +
                ", radarip='" + radarip + '\'' +
                ", port='" + port + '\'' +
                ", sirialnum='" + sirialnum + '\'' +
                ", workstate=" + workstate +
                ", foreignmatter=" + foreignmatter +
                ", safetydoor=" + safetydoor +
                ", doorstate=" + doorstate +
                ", radarerror=" + radarerror +
                ", lastlog='" + lastlog + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}