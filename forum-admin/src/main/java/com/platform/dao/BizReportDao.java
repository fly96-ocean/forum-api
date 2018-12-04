package com.platform.dao;

import com.platform.entity.BizReportEntity;

import java.util.List;
import java.util.Map;

/**
 * @author felix
 */
public interface BizReportDao extends BaseDao<BizReportEntity> {
    BizReportEntity queryReportArticleObject(Long oId);
    BizReportEntity queryReportCommentObject(Long oId);
    BizReportEntity queryReportUserObject(Long oId);

    List<BizReportEntity> queryReportArticleList(Map<String, Object> map);
    List<BizReportEntity> queryReportCommentList(Map<String, Object> map);
    List<BizReportEntity> queryReportUserList(Map<String, Object> map);

}
