package com.platform.entity;


import java.io.Serializable;

public class UserTagVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer oId;
    private Integer userOid;
    private Integer tagOid;
    private Integer type;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getUserOid() {
        return userOid;
    }

    public void setUserOid(Integer userOid) {
        this.userOid = userOid;
    }

    public Integer getTagOid() {
        return tagOid;
    }

    public void setTagOid(Integer tagOid) {
        this.tagOid = tagOid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
