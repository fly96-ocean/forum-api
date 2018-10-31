package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class LivenessVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private String oId;
    //用户 id
    private Integer livenessUserId;
    //活跃度记录日期
    private String livenessDate;
    //积分计数
    private String livenessPoint;
    //发帖计数
    private String livenessArticle;
    //回帖计数
    private String livenessComment;
    //活动计数
    private String livenessActivity;
    //感谢计数
    private String livenessThank;
    //投票计数
    private String livenessVote;
    //打赏计数
    private String livenessReward;
    //PV 计数
    private String livenessPV;
    //采纳回答计数
    private String livenessAcceptAnswer;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public Integer getLivenessUserId() {
        return livenessUserId;
    }

    public void setLivenessUserId(Integer livenessUserId) {
        this.livenessUserId = livenessUserId;
    }

    public String getLivenessDate() {
        return livenessDate;
    }

    public void setLivenessDate(String livenessDate) {
        this.livenessDate = livenessDate;
    }

    public String getLivenessPoint() {
        return livenessPoint;
    }

    public void setLivenessPoint(String livenessPoint) {
        this.livenessPoint = livenessPoint;
    }

    public String getLivenessArticle() {
        return livenessArticle;
    }

    public void setLivenessArticle(String livenessArticle) {
        this.livenessArticle = livenessArticle;
    }

    public String getLivenessComment() {
        return livenessComment;
    }

    public void setLivenessComment(String livenessComment) {
        this.livenessComment = livenessComment;
    }

    public String getLivenessActivity() {
        return livenessActivity;
    }

    public void setLivenessActivity(String livenessActivity) {
        this.livenessActivity = livenessActivity;
    }

    public String getLivenessThank() {
        return livenessThank;
    }

    public void setLivenessThank(String livenessThank) {
        this.livenessThank = livenessThank;
    }

    public String getLivenessVote() {
        return livenessVote;
    }

    public void setLivenessVote(String livenessVote) {
        this.livenessVote = livenessVote;
    }

    public String getLivenessReward() {
        return livenessReward;
    }

    public void setLivenessReward(String livenessReward) {
        this.livenessReward = livenessReward;
    }

    public String getLivenessPV() {
        return livenessPV;
    }

    public void setLivenessPV(String livenessPV) {
        this.livenessPV = livenessPV;
    }

    public String getLivenessAcceptAnswer() {
        return livenessAcceptAnswer;
    }

    public void setLivenessAcceptAnswer(String livenessAcceptAnswer) {
        this.livenessAcceptAnswer = livenessAcceptAnswer;
    }
}
