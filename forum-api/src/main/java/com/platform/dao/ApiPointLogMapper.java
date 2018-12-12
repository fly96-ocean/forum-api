package com.platform.dao;

import com.platform.entity.PointLogVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiPointLogMapper extends BaseDao<PointLogVo> {
    Integer querySum(Map<String, Object> map);

    /*** 服务器端接口 ****/
    List<Map<Date, Integer>> pointTotal(Map<String, Object> map);

}
