
package com.platform.entity;

import java.io.Serializable;

public class TagVo  implements Serializable {
    //主键
    private Integer oId;
    //标签引用（帖子/用户自评等）计数
    private Integer tagReferenceCount;
    //标签回帖计数
    private Integer tagCommentCount;
    //标签关注者计数
    private Integer tagFollowerCount;
    //标签链接引用计数
    private Integer tagLinkCount;
    //标签标题
    private String tagTitle;
    //标签访问路径
    private String tagURI;
    //标签描述
    private String tagDescription;
    //标签图标路径
    private String tagIconPath;
    //标签 CSS
    private String tagCSS;
    //0：正常，1：封禁
    private Integer tagStatus;
    //标签点赞计数
    private Integer tagGoodCnt;
    //标签点踩计数
    private Integer tagBadCnt;
    //标签 SEO 标题
    private String tagSeoTitle;
    //标签 SEO 关键字
    private String tagSeoKeywords;
    //标签 SEO 描述
    private String tagSeoDesc;
    //标签随机数，用于快速选择随机标签
    private Double tagRandomDouble;
    //标签广告
    private String tagAd;
    //是否显示全站侧边栏广告
    private Integer tagShowSideAd;
    //标签样式
    private String tagStyle;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getTagReferenceCount() {
        return tagReferenceCount;
    }

    public void setTagReferenceCount(Integer tagReferenceCount) {
        this.tagReferenceCount = tagReferenceCount;
    }

    public Integer getTagCommentCount() {
        return tagCommentCount;
    }

    public void setTagCommentCount(Integer tagCommentCount) {
        this.tagCommentCount = tagCommentCount;
    }

    public Integer getTagFollowerCount() {
        return tagFollowerCount;
    }

    public void setTagFollowerCount(Integer tagFollowerCount) {
        this.tagFollowerCount = tagFollowerCount;
    }

    public Integer getTagLinkCount() {
        return tagLinkCount;
    }

    public void setTagLinkCount(Integer tagLinkCount) {
        this.tagLinkCount = tagLinkCount;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public String getTagURI() {
        return tagURI;
    }

    public void setTagURI(String tagURI) {
        this.tagURI = tagURI;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public String getTagIconPath() {
        return tagIconPath;
    }

    public void setTagIconPath(String tagIconPath) {
        this.tagIconPath = tagIconPath;
    }

    public String getTagCSS() {
        return tagCSS;
    }

    public void setTagCSS(String tagCSS) {
        this.tagCSS = tagCSS;
    }

    public Integer getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(Integer tagStatus) {
        this.tagStatus = tagStatus;
    }

    public Integer getTagGoodCnt() {
        return tagGoodCnt;
    }

    public void setTagGoodCnt(Integer tagGoodCnt) {
        this.tagGoodCnt = tagGoodCnt;
    }

    public Integer getTagBadCnt() {
        return tagBadCnt;
    }

    public void setTagBadCnt(Integer tagBadCnt) {
        this.tagBadCnt = tagBadCnt;
    }

    public String getTagSeoTitle() {
        return tagSeoTitle;
    }

    public void setTagSeoTitle(String tagSeoTitle) {
        this.tagSeoTitle = tagSeoTitle;
    }

    public String getTagSeoKeywords() {
        return tagSeoKeywords;
    }

    public void setTagSeoKeywords(String tagSeoKeywords) {
        this.tagSeoKeywords = tagSeoKeywords;
    }

    public String getTagSeoDesc() {
        return tagSeoDesc;
    }

    public void setTagSeoDesc(String tagSeoDesc) {
        this.tagSeoDesc = tagSeoDesc;
    }

    public Double getTagRandomDouble() {
        return tagRandomDouble;
    }

    public void setTagRandomDouble(Double tagRandomDouble) {
        this.tagRandomDouble = tagRandomDouble;
    }

    public String getTagAd() {
        return tagAd;
    }

    public void setTagAd(String tagAd) {
        this.tagAd = tagAd;
    }

    public Integer getTagShowSideAd() {
        return tagShowSideAd;
    }

    public void setTagShowSideAd(Integer tagShowSideAd) {
        this.tagShowSideAd = tagShowSideAd;
    }

    public String getTagStyle() {
        return tagStyle;
    }

    public void setTagStyle(String tagStyle) {
        this.tagStyle = tagStyle;
    }
}
