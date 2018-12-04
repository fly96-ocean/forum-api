package com.platform.service;

import com.platform.dao.ApiDomainMapper;
import com.platform.entity.ArticleVo;
import com.platform.entity.DomainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiDomainService {
    @Autowired
    private ApiDomainMapper domainDao;


    public DomainVo queryObject(Long id) {
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


    public void deleteOrNot(Long[] ids, Integer status) {
        for(int i = 0; i<ids.length; i++){
            if(ids[i]!=null){
                DomainVo domain = domainDao.queryObject(ids[i]);
                domain.setDomainStatus(status);
                domainDao.update(domain);
            }

        }
    }

    public int serverQueryTotal(Map<String, Object> map){
        return domainDao.serverQueryTotal(map);
    }

    public List<DomainVo> serverQueryList(Map<String, Object> map){
        return domainDao.serverQueryList(map);
    }


}
