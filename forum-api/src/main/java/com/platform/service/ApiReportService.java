package com.platform.service;

import com.platform.dao.*;
import com.platform.entity.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class ApiReportService {

    @Autowired
    private ApiReportMapper reportDao;


    public ReportVo queryObject(Long id) {
        return reportDao.queryObject(id);
    }


    public List<ReportVo> queryList(Map<String, Object> map) {
        return reportDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return reportDao.queryTotal(map);
    }


    public void save(ReportVo reportVo) {
        reportDao.save(reportVo);
    }


    public void update(ReportVo reportVo) {
        reportDao.update(reportVo);
    }


    public void delete(Integer id) {
        reportDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        reportDao.deleteBatch(ids);
    }


}
