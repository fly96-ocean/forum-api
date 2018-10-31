package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class ArticleTagVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer oId;
    //帖子 id
    private Integer articleOId;
    //标签 id
    private Integer tagOId;
    //帖子回帖计数
    private Integer articleCommentCount;
    //帖子最近一次回帖时间
    private Date articleLatestCmtTime;
    //帖子 Reddit 算法评分       （可以暂时不考虑）
    private Double redditScore;
    //0：帖子不是优选，1：帖子是优选
    private String articlePerfect;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getArticleOId() {
        return articleOId;
    }

    public void setArticleOId(Integer articleOId) {
        this.articleOId = articleOId;
    }

    public Integer getTagOId() {
        return tagOId;
    }

    public void setTagOId(Integer tagOId) {
        this.tagOId = tagOId;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Date getArticleLatestCmtTime() {
        return articleLatestCmtTime;
    }

    public void setArticleLatestCmtTime(Date articleLatestCmtTime) {
        this.articleLatestCmtTime = articleLatestCmtTime;
    }

    public Double getRedditScore() {
        return redditScore;
    }

    public void setRedditScore(Double redditScore) {
        this.redditScore = redditScore;
    }

    public String getArticlePerfect() {
        return articlePerfect;
    }

    public void setArticlePerfect(String articlePerfect) {
        this.articlePerfect = articlePerfect;
    }
}
