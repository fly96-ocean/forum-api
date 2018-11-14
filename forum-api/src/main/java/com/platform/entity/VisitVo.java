package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class VisitVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long oId;
    //浏览者 id
    private Long visitUserId;
    //被浏览实体 id
    private Long visitedId;
    //0：用户，1：帖子，2，回帖
    private Integer visitType;
    //浏览链接
    private String visitURL;
    // IP
    private String visitIP;
    //User-Agent
    private String visitUA;
    //浏览者城市
    private String visitCity;
    //设备唯一标识
    private String visitDeviceId;
    //上游链接
    private String visitRefererURL;
    //统计时间
    private Date visitCreated;
    //统计过期时间
    private Date visitExpired;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getVisitUserId() {
        return visitUserId;
    }

    public void setVisitUserId(Long visitUserId) {
        this.visitUserId = visitUserId;
    }

    public Long getVisitedId() {
        return visitedId;
    }

    public void setVisitedId(Long visitedId) {
        this.visitedId = visitedId;
    }

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }

    public String getVisitURL() {
        return visitURL;
    }

    public void setVisitURL(String visitURL) {
        this.visitURL = visitURL;
    }

    public String getVisitIP() {
        return visitIP;
    }

    public void setVisitIP(String visitIP) {
        this.visitIP = visitIP;
    }

    public String getVisitUA() {
        return visitUA;
    }

    public void setVisitUA(String visitUA) {
        this.visitUA = visitUA;
    }

    public String getVisitCity() {
        return visitCity;
    }

    public void setVisitCity(String visitCity) {
        this.visitCity = visitCity;
    }

    public String getVisitDeviceId() {
        return visitDeviceId;
    }

    public void setVisitDeviceId(String visitDeviceId) {
        this.visitDeviceId = visitDeviceId;
    }

    public String getVisitRefererURL() {
        return visitRefererURL;
    }

    public void setVisitRefererURL(String visitRefererURL) {
        this.visitRefererURL = visitRefererURL;
    }

    public Date getVisitCreated() {
        return visitCreated;
    }

    public void setVisitCreated(Date visitCreated) {
        this.visitCreated = visitCreated;
    }

    public Date getVisitExpired() {
        return visitExpired;
    }

    public void setVisitExpired(Date visitExpired) {
        this.visitExpired = visitExpired;
    }
}
