package com.platform.service;

import com.platform.dao.ApiVisitMapper;
import com.platform.entity.VisitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiVisitService {

    @Autowired
    private ApiVisitMapper visitDao;


    public VisitVo queryObject(Long id) {
        return visitDao.queryObject(id);
    }


    public List<VisitVo> queryList(Map<String, Object> map) {
        return visitDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return visitDao.queryTotal(map);
    }


    public void save(VisitVo visitVo) {
        visitDao.save(visitVo);
    }


    public void update(VisitVo visitVo) {
        visitDao.update(visitVo);
    }


}
