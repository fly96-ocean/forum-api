package com.platform.service;

import com.platform.dao.ApiUserMapper;
import com.platform.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiUserService {
    @Autowired
    private ApiUserMapper userDao;

    public UserVo queryObject(Integer userId) {
        return userDao.queryObject(userId);
    }

    public List<UserVo> queryList(Map<String, Object> map) {
        return userDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return userDao.queryTotal(map);
    }

    public void update(UserVo userVo) {
        userDao.update(userVo);
    }

}
