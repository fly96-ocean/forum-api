package com.platform.dao;

import com.platform.entity.TagTagVo;
import com.platform.entity.TagVo;

import java.util.List;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiTagTagMapper extends BaseDao<TagTagVo> {
    List<TagTagVo> queryOId1List();
    List<TagTagVo> queryOId2List(Long oId);
}
