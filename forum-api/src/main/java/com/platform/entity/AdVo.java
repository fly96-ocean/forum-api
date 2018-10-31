package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class AdVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //形式  0：图片   1：视频  （目前只有图片）
    private Integer media_type;
    //广告名称
    private String name;
    //链接
    private String link;
    //图片
    private String imageUrl;
    //内容
    private String content;
    //结束时间
    private Date endTime;
    //状态
    private Integer enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedia_type() {
        return media_type;
    }

    public void setMedia_type(Integer media_type) {
        this.media_type = media_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}