package com.platform.service.impl;

import com.google.common.collect.Lists;
import com.platform.dao.BizArticleDao;
import com.platform.dao.BizCommentDao;
import com.platform.dao.BizForumUserDao;
import com.platform.dao.BizReportDao;
import com.platform.entity.*;
import com.platform.service.BizForumUserService;
import com.platform.service.BizReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("bizForumUserService")
public class BizForumUserServiceImpl implements BizForumUserService {

    @Autowired
    private BizForumUserDao forumUserDao;

    @Override
    public BizForumUserEntity queryObject(Long id) {
        return forumUserDao.queryObject(id);
    }

    @Override
    public List<BizForumUserEntity> queryList(Map<String, Object> map) {
        return forumUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return forumUserDao.queryTotal(map);
    }

    @Override
    public void save(BizForumUserEntity forumUserEntity) {
        forumUserDao.save(forumUserEntity);
    }

    @Override
    public void update(BizForumUserEntity forumUserEntity) {
        forumUserDao.update(forumUserEntity);
    }

    @Transactional
    public void deleteOrNot(Long[] ids, Integer status){
        for(int i = 0; i<ids.length; i++){
            if(ids[i]!=null){
                BizForumUserEntity forumUserEntity = forumUserDao.queryObject(ids[i]);
                forumUserEntity.setUserStatus(status);
                forumUserDao.update(forumUserEntity);
            }

        }
    }
}
