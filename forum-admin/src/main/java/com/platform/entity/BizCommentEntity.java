package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class BizCommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long oId;
    //回帖正文内容
    private String commentContent;
    //回帖创建时间
    private Date commentCreateTime;
    //回帖作者 id
    private Long commentAuthorId;
    //回帖的帖子 id
    private Long commentOnArticleId;
    //回帖带锚点 URL
    private String commentSharpURL;
    //回复针对回帖 id，即 父回帖 id
    private Long commentOriginalCommentId;
    //0：正常，1：封禁
    private Integer commentStatus;
    //回帖 IP
    private String commentIP;
    //User-Agent
    private String commentUA;
    //0：公开回帖，1：匿名回帖
    private Integer commentAnonymous;
    //回帖点赞计数
    private Integer commentGoodCnt;
    //回帖点踩计数
    private Integer commentBadCnt;
    //回帖评分
    private Double commentScore;
    //回复计数
    private Integer commentReplyCnt;
    //回帖语音文件 URL
    private String commentAudioURL;
    //0：回帖未被采纳，1：回帖已被采纳（仅作用于问答帖）
    private Integer commentQnAOffered;
    //0：所有人可见，1：仅楼主和自己可见
    private Integer commentVisible;
    //当前用户id
    private Long currentUserId;
    //回帖人头像
    private String userAvatarUrl;
    //回帖人昵称
    private String userNickName;
    //是否点赞
    private Integer hasGood;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Long getCommentAuthorId() {
        return commentAuthorId;
    }

    public void setCommentAuthorId(Long commentAuthorId) {
        this.commentAuthorId = commentAuthorId;
    }

    public Long getCommentOnArticleId() {
        return commentOnArticleId;
    }

    public void setCommentOnArticleId(Long commentOnArticleId) {
        this.commentOnArticleId = commentOnArticleId;
    }

    public String getCommentSharpURL() {
        return commentSharpURL;
    }

    public void setCommentSharpURL(String commentSharpURL) {
        this.commentSharpURL = commentSharpURL;
    }

    public Long getCommentOriginalCommentId() {
        return commentOriginalCommentId;
    }

    public void setCommentOriginalCommentId(Long commentOriginalCommentId) {
        this.commentOriginalCommentId = commentOriginalCommentId;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getCommentIP() {
        return commentIP;
    }

    public void setCommentIP(String commentIP) {
        this.commentIP = commentIP;
    }

    public String getCommentUA() {
        return commentUA;
    }

    public void setCommentUA(String commentUA) {
        this.commentUA = commentUA;
    }

    public Integer getCommentAnonymous() {
        return commentAnonymous;
    }

    public void setCommentAnonymous(Integer commentAnonymous) {
        this.commentAnonymous = commentAnonymous;
    }

    public Integer getCommentGoodCnt() {
        return commentGoodCnt;
    }

    public void setCommentGoodCnt(Integer commentGoodCnt) {
        this.commentGoodCnt = commentGoodCnt;
    }

    public Integer getCommentBadCnt() {
        return commentBadCnt;
    }

    public void setCommentBadCnt(Integer commentBadCnt) {
        this.commentBadCnt = commentBadCnt;
    }

    public Double getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Double commentScore) {
        this.commentScore = commentScore;
    }

    public Integer getCommentReplyCnt() {
        return commentReplyCnt;
    }

    public void setCommentReplyCnt(Integer commentReplyCnt) {
        this.commentReplyCnt = commentReplyCnt;
    }

    public String getCommentAudioURL() {
        return commentAudioURL;
    }

    public void setCommentAudioURL(String commentAudioURL) {
        this.commentAudioURL = commentAudioURL;
    }

    public Integer getCommentQnAOffered() {
        return commentQnAOffered;
    }

    public void setCommentQnAOffered(Integer commentQnAOffered) {
        this.commentQnAOffered = commentQnAOffered;
    }

    public Integer getCommentVisible() {
        return commentVisible;
    }

    public void setCommentVisible(Integer commentVisible) {
        this.commentVisible = commentVisible;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getHasGood() {
        return hasGood;
    }

    public void setHasGood(Integer hasGood) {
        this.hasGood = hasGood;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }
}
