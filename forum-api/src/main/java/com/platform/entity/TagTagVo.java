
package com.platform.entity;

import java.io.Serializable;

public class TagTagVo implements Serializable {
    //主键
    private Long oId;
    //标签1 id
    private Long tag1Oid;
    //标签2 id
    private Long tag2Oid;
    //权重
    private String weight;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getTag1Oid() {
        return tag1Oid;
    }

    public void setTag1Oid(Long tag1Oid) {
        this.tag1Oid = tag1Oid;
    }

    public Long getTag2Oid() {
        return tag2Oid;
    }

    public void setTag2Oid(Long tag2Oid) {
        this.tag2Oid = tag2Oid;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
