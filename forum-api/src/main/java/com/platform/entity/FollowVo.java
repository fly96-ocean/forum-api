package com.platform.entity;

import java.io.Serializable;

public class FollowVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long oId;
    //关注者 id
    private Long followerId;
    //关注实体 id
    private Long followingId;
    //0：用户，1：标签，2：帖子收藏，3：帖子关注  4：点赞   5：差评  6：作者关注
    private Integer followingType;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public Integer getFollowingType() {
        return followingType;
    }

    public void setFollowingType(Integer followingType) {
        this.followingType = followingType;
    }
}
