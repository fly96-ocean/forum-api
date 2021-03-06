package com.platform.dao;

import com.platform.entity.ArticleVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiArticleMapper extends BaseDao<ArticleVo> {
    List<ArticleVo> queryMyCommentArticle(Map<String, Object> map);
    List<ArticleVo> queryMyCollectArticle(Map<String, Object> map);
    List<ArticleVo> queryMyZanArticle(Map<String, Object> map);
    List<ArticleVo> queryMyVisitArticle(Map<String, Object> map);
    List<ArticleVo> queryPerfectArticle();

    /*** 服务器端接口 ****/
    List<ArticleVo> serverQueryList(Map<String, Object> map);
    int serverQueryTotal(Map<String, Object> map);
    List<Map<Date, Integer>> publishArticleTotal(Map<String, Object> map);
}
