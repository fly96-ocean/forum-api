package com.platform.service;

import com.platform.dao.ApiDomainMapper;
import com.platform.entity.DomainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiDomainService {
    @Autowired
    private ApiDomainMapper domainDao;


    public DomainVo queryObject(Integer id) {
        return  domainDao.queryObject(id);
    }


    public List<DomainVo> queryList(Map<String, Object> map) {
        return domainDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return domainDao.queryTotal(map);
    }


    public void save(DomainVo domainVo) {
        domainDao.save(domainVo);
    }


    public void update(DomainVo domainVo) {
        domainDao.update(domainVo);
    }


    public void delete(Integer id) {
        domainDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        domainDao.deleteBatch(ids);
    }

}
