package com.platform.service.impl;

import com.google.common.collect.Lists;
import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.service.BizReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("bizReportService")
public class BizReportServiceImpl implements BizReportService {
    @Autowired
    private BizReportDao reportDao;
    @Autowired
    private BizArticleDao articleDao;
    @Autowired
    private BizCommentDao commentDao;
    @Autowired
    private BizForumUserDao userDao;


    @Override
    public BizReportEntity queryObject(Long id) {
        BizReportEntity report = reportDao.queryObject(id);
        BizReportEntity result = new BizReportEntity();
        if(report.getReportType() == 0){
            result = reportDao.queryReportArticleObject(id);
        }else if(report.getReportType() == 1){
            result = reportDao.queryReportCommentObject(id);
        }else if(report.getReportType() == 2){
            result = reportDao.queryReportUserObject(id);
        }
        return result;
    }

    @Override
    public List<BizReportEntity> queryList(Map<String, Object> map) {
        List<BizReportEntity> result = Lists.newArrayList();
        String reportTypeStr = (String) map.get("reportDataType");
        Integer reportType =  Integer.parseInt(reportTypeStr);
        if(reportType == 0){
            result = reportDao.queryReportArticleList(map);
        }else if(reportType == 1){
            result = reportDao.queryReportCommentList(map);
        }else if(reportType == 2){
            result = reportDao.queryReportUserList(map);
        }
        return result;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return reportDao.queryTotal(map);
    }

    @Override
    public void save(BizReportEntity reportEntity) {
        reportDao.save(reportEntity);
    }

    @Override
    public void update(BizReportEntity reportEntity) {
        reportDao.update(reportEntity);
    }


    /***
     *
     * @param ids
     * @param type  0: 未处理  1：已处理  2：已忽略
     */
    @Transactional
    public void handleOrIgnore(Long[] ids, Integer type){
        for(int i = 0; i<ids.length; i++){
            if(ids[i]!=null){
                BizReportEntity report = reportDao.queryObject(ids[i]);
                report.setReportHandled(type);
                report.setReportHandleTime(new Date());
                reportDao.update(report);

                Integer dataType = report.getReportDataType();
                Long dataId = report.getReportDataId();
                if(type == 1){
                    if(dataType == 0){//帖子
                        BizArticleEntity articleEntity = articleDao.queryObject(dataId);
                        articleEntity.setArticleStatus(1);
                        articleDao.update(articleEntity);
                    }else if(dataType == 1){//回帖
                        BizCommentEntity commentEntity = commentDao.queryObject(dataId);
                        commentEntity.setCommentStatus(1);
                        commentDao.update(commentEntity);
                    }else if(dataType == 2){//用户
                        BizForumUserEntity userEntity = userDao.queryObject(dataId);
                        userEntity.setUserStatus(1);
                        userDao.update(userEntity);
                    }
                }


            }
        }
    }


}
