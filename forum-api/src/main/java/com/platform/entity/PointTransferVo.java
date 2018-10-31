package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class PointTransferVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer oId;
    private Integer fromId;
    private Integer toId;
    private Integer sum;
    private Integer fromBalance;
    private Integer toBalance;
    private Date time;
    private Integer type;
    private Integer dataId;
    private String memo;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getFromBalance() {
        return fromBalance;
    }

    public void setFromBalance(Integer fromBalance) {
        this.fromBalance = fromBalance;
    }

    public Integer getToBalance() {
        return toBalance;
    }

    public void setToBalance(Integer toBalance) {
        this.toBalance = toBalance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
