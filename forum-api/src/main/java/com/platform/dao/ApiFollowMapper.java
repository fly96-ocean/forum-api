package com.platform.dao;

import com.platform.entity.FollowVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiFollowMapper extends BaseDao<FollowVo> {
    public void deleteByArticleIdAndUserId(@Param("articleId") Long articleId, @Param("userId")Long userId);
    public void deleteByFollowerIdAndFollowingId(@Param("followerId") Long followerId, @Param("followingId")Long followingId);
}
