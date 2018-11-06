package com.platform.entity;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DomainVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer oId;
    //领域标题
    private String domainTitle;
    //领域访问路径     （可以暂时不考虑）
    private String domainURI;
    //领域描述
    private String domainDescription;
    //领域类型  0、图文帖子；1、短视频帖子；2、问答帖子
    private Integer domainType;
    //领域排序
    private Integer domainSort;
    //0：作为导航，1：不作为导航    （可以暂时不考虑）
    private Integer domainNav;
    //领域标签计数     （可以暂时不考虑）
    private Integer domainTagCnt;
    //领域图片路径    （可以暂时不考虑）
    private String domainIconPath;
    //领域 CSS    （可以暂时不考虑）
    private String domainCSS;
    //0：正常，1：封禁
    private Integer domainStatus;
    //领域 SEO 标题    （可以暂时不考虑）
    private String domainSeoTitle;
    //领域 SEO 关键字    （可以暂时不考虑）
    private String domainSeoKeywords;
    //领域 SEO 描述    （可以暂时不考虑）
    private String domainSeoDesc;
    //domain下的帖子
    private List<ArticleVo> articles = new ArrayList<>();


    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getDomainTitle() {
        return domainTitle;
    }

    public void setDomainTitle(String domainTitle) {
        this.domainTitle = domainTitle;
    }

    public String getDomainURI() {
        return domainURI;
    }

    public void setDomainURI(String domainURI) {
        this.domainURI = domainURI;
    }

    public String getDomainDescription() {
        return domainDescription;
    }

    public void setDomainDescription(String domainDescription) {
        this.domainDescription = domainDescription;
    }

    public Integer getDomainType() {
        return domainType;
    }

    public void setDomainType(Integer domainType) {
        this.domainType = domainType;
    }

    public Integer getDomainSort() {
        return domainSort;
    }

    public void setDomainSort(Integer domainSort) {
        this.domainSort = domainSort;
    }

    public Integer getDomainNav() {
        return domainNav;
    }

    public void setDomainNav(Integer domainNav) {
        this.domainNav = domainNav;
    }

    public Integer getDomainTagCnt() {
        return domainTagCnt;
    }

    public void setDomainTagCnt(Integer domainTagCnt) {
        this.domainTagCnt = domainTagCnt;
    }

    public String getDomainIconPath() {
        return domainIconPath;
    }

    public void setDomainIconPath(String domainIconPath) {
        this.domainIconPath = domainIconPath;
    }

    public String getDomainCSS() {
        return domainCSS;
    }

    public void setDomainCSS(String domainCSS) {
        this.domainCSS = domainCSS;
    }

    public Integer getDomainStatus() {
        return domainStatus;
    }

    public void setDomainStatus(Integer domainStatus) {
        this.domainStatus = domainStatus;
    }

    public String getDomainSeoTitle() {
        return domainSeoTitle;
    }

    public void setDomainSeoTitle(String domainSeoTitle) {
        this.domainSeoTitle = domainSeoTitle;
    }

    public String getDomainSeoKeywords() {
        return domainSeoKeywords;
    }

    public void setDomainSeoKeywords(String domainSeoKeywords) {
        this.domainSeoKeywords = domainSeoKeywords;
    }

    public String getDomainSeoDesc() {
        return domainSeoDesc;
    }

    public void setDomainSeoDesc(String domainSeoDesc) {
        this.domainSeoDesc = domainSeoDesc;
    }

    public List<ArticleVo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleVo> articles) {
        this.articles = articles;
    }
}
