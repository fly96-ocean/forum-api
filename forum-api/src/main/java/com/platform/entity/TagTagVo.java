
package com.platform.entity;

import java.io.Serializable;

public class TagTagVo implements Serializable {
    private Integer oId;
    private Integer tag1Oid;
    private Integer tag2Oid;
    private String weight;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getTag1Oid() {
        return tag1Oid;
    }

    public void setTag1Oid(Integer tag1Oid) {
        this.tag1Oid = tag1Oid;
    }

    public Integer getTag2Oid() {
        return tag2Oid;
    }

    public void setTag2Oid(Integer tag2Oid) {
        this.tag2Oid = tag2Oid;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
