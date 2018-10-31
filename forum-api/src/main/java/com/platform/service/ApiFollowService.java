package com.platform.service;

import com.platform.dao.ApiFollowMapper;
import com.platform.entity.FollowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiFollowService {

    @Autowired
    private ApiFollowMapper followDao;

    public FollowVo queryObject(Integer id) {
        return followDao.queryObject(id);
    }


    public List<FollowVo> queryList() {
        return followDao.queryList();
    }


    public int queryTotal(Map<String, Object> map) {
        return followDao.queryTotal(map);
    }


    public void save(FollowVo followVo) {
        followDao.save(followVo);
    }


    public void update(FollowVo followVo) {
        followDao.update(followVo);
    }



}
