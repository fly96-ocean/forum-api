package com.platform.dao;

import com.platform.entity.PointLogVo;

import java.util.Map;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiPointLogMapper extends BaseDao<PointLogVo> {
    int querySum(Map<String, Object> map);
}
