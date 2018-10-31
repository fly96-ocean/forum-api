package com.platform.entity;

import java.io.Serializable;

public class RevisionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer oId;
    private Integer revisionDataType;
    private Integer revisionDataId;
    private String revisionData;
    private Integer revisionAuthorId;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getRevisionDataType() {
        return revisionDataType;
    }

    public void setRevisionDataType(Integer revisionDataType) {
        this.revisionDataType = revisionDataType;
    }

    public Integer getRevisionDataId() {
        return revisionDataId;
    }

    public void setRevisionDataId(Integer revisionDataId) {
        this.revisionDataId = revisionDataId;
    }

    public String getRevisionData() {
        return revisionData;
    }

    public void setRevisionData(String revisionData) {
        this.revisionData = revisionData;
    }

    public Integer getRevisionAuthorId() {
        return revisionAuthorId;
    }

    public void setRevisionAuthorId(Integer revisionAuthorId) {
        this.revisionAuthorId = revisionAuthorId;
    }
}
