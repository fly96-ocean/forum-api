
package com.platform.entity;

import java.io.Serializable;

public class TagVo  implements Serializable {
    private Integer oId;
    private Integer tagReferenceCount;
    private Integer tagCommentCount;
    private Integer tagFollowerCount;
    private Integer tagLinkCount;
    private String tagTitle;
    private String tagURI;
    private String tagDescription;
    private String tagIconPath;
    private String tagCSS;
    private Integer tagStatus;
    private Integer tagGoodCnt;
    private Integer tagBadCnt;
    private String tagSeoTitle;
    private String tagSeoKeywords;
    private String tagSeoDesc;
    private Double tagRandomDouble;
    private String tagAd;
    private Integer tagShowSideAd;

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
}
