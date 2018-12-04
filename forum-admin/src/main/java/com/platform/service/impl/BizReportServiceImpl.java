package com.platform.service.impl;

import com.google.common.collect.Lists;
import com.platform.dao.BizReportDao;
import com.platform.dao.SysOssDao;
import com.platform.entity.BizReportEntity;
import com.platform.entity.SysOssEntity;
import com.platform.service.BizReportService;
import com.platform.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("bizReportService")
public class BizReportServiceImpl implements BizReportService {
    @Autowired
    private BizReportDao reportDao;

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
        String reportTypeStr = (String) map.get("reportType");
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


}
