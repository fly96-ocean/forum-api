package com.platform.entity;

import java.io.Serializable;

public class OptionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer oId;
    private String optionValue;
    private String optionCategory;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionCategory() {
        return optionCategory;
    }

    public void setOptionCategory(String optionCategory) {
        this.optionCategory = optionCategory;
    }
}
