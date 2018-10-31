package com.platform.entity;

import java.io.Serializable;

public class DomainTagVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer oId;
    //领域 id
    private Integer domainOId;
    //标签 id
    private Integer tagOId;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getDomainOId() {
        return domainOId;
    }

    public void setDomainOId(Integer domainOId) {
        this.domainOId = domainOId;
    }

    public Integer getTagOId() {
        return tagOId;
    }

    public void setTagOId(Integer tagOId) {
        this.tagOId = tagOId;
    }
}
