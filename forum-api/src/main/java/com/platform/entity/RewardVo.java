package com.platform.entity;

import java.io.Serializable;

public class RewardVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer oId;
    private Integer senderId;
    private Integer dataId;
    private Integer type;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
