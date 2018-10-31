package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class PointLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    private Long oId;
    //帖子 id
    private Long pointLogArticleId;
    //建帖或者回帖用户 id
    private Long pointLogArticleAuthorId;
    //0：建帖积分，1：回帖积分
    private Integer pointLogType;

    //积分数记录
    private Integer pointLogPoint;

    //积分记录时间
    private Date pointLogCreateTime;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getPointLogArticleId() {
        return pointLogArticleId;
    }

    public void setPointLogArticleId(Long pointLogArticleId) {
        this.pointLogArticleId = pointLogArticleId;
    }

    public Long getPointLogArticleAuthorId() {
        return pointLogArticleAuthorId;
    }

    public void setPointLogArticleAuthorId(Long pointLogArticleAuthorId) {
        this.pointLogArticleAuthorId = pointLogArticleAuthorId;
    }

    public Integer getPointLogType() {
        return pointLogType;
    }

    public void setPointLogType(Integer pointLogType) {
        this.pointLogType = pointLogType;
    }

    public Integer getPointLogPoint() {
        return pointLogPoint;
    }

    public void setPointLogPoint(Integer pointLogPoint) {
        this.pointLogPoint = pointLogPoint;
    }

    public Date getPointLogCreateTime() {
        return pointLogCreateTime;
    }

    public void setPointLogCreateTime(Date pointLogCreateTime) {
        this.pointLogCreateTime = pointLogCreateTime;
    }
}
