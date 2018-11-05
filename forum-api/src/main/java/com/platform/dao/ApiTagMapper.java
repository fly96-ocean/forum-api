package com.platform.dao;

import com.platform.entity.FollowVo;
import com.platform.entity.TagVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiTagMapper extends BaseDao<TagVo> {
    public List<TagVo> queryByIds(@Param("oIds") List<Long> oIds);
}
