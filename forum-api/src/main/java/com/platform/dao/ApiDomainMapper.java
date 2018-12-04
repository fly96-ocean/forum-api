package com.platform.dao;

import com.platform.entity.ArticleVo;
import com.platform.entity.DomainVo;

import java.util.List;
import java.util.Map;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiDomainMapper extends BaseDao<DomainVo> {
    /*** 服务器端接口 ****/
    List<DomainVo> serverQueryList(Map<String, Object> map);
    int serverQueryTotal(Map<String, Object> map);
}
