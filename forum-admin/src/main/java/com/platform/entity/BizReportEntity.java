package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class BizReportEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long oId;
    //举报人 id
    private Long reportUserId;
    //举报数据 id
    private Long reportDataId;
    //0：帖子，1：回帖，2：用户
    private Integer reportDataType;
    //0：（内容）垃圾广告，1：（内容）色情低俗，2：（内容）违法违规，3：（内容）涉嫌侵权，4：（内容）人身攻击，5：（用户）冒充账号，6：（用户）垃圾广告账号，7：（用户）个人信息违规，49：其他
    private Integer reportType;
    //举报情况备注
    private String reportMemo;
    //0：未处理，1：已处理  2: 已忽略
    private Integer reportHandled;
    //举报创建时间
    private Date reportCreateTime;
    //处理时间
    private Date reportHandleTime;

    //举报人信息
    private BizUserEntity reportUser;
    //举报帖数据
    private BizArticleEntity reportedArticle;
    //举报回帖数据
    private BizCommentEntity reportedComment;
    //举报会员数据
    private BizUserEntity reportedUser;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(Long reportUserId) {
        this.reportUserId = reportUserId;
    }

    public Long getReportDataId() {
        return reportDataId;
    }

    public void setReportDataId(Long reportDataId) {
        this.reportDataId = reportDataId;
    }

    public Integer getReportDataType() {
        return reportDataType;
    }

    public void setReportDataType(Integer reportDataType) {
        this.reportDataType = reportDataType;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReportMemo() {
        return reportMemo;
    }

    public void setReportMemo(String reportMemo) {
        this.reportMemo = reportMemo;
    }

    public Integer getReportHandled() {
        return reportHandled;
    }

    public void setReportHandled(Integer reportHandled) {
        this.reportHandled = reportHandled;
    }

    public Date getReportCreateTime() {
        return reportCreateTime;
    }

    public void setReportCreateTime(Date reportCreateTime) {
        this.reportCreateTime = reportCreateTime;
    }

    public Date getReportHandleTime() {
        return reportHandleTime;
    }

    public void setReportHandleTime(Date reportHandleTime) {
        this.reportHandleTime = reportHandleTime;
    }

    public BizUserEntity getReportUser() {
        return reportUser;
    }

    public void setReportUser(BizUserEntity reportUser) {
        this.reportUser = reportUser;
    }

    public BizArticleEntity getReportedArticle() {
        return reportedArticle;
    }

    public void setReportedArticle(BizArticleEntity reportedArticle) {
        this.reportedArticle = reportedArticle;
    }

    public BizCommentEntity getReportedComment() {
        return reportedComment;
    }

    public void setReportedComment(BizCommentEntity reportedComment) {
        this.reportedComment = reportedComment;
    }

    public BizUserEntity getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(BizUserEntity reportedUser) {
        this.reportedUser = reportedUser;
    }
}
