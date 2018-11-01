package com.platform.service;

import com.platform.dao.ApiArticleMapper;
import com.platform.dao.ApiFollowMapper;
import com.platform.dao.ApiPointLogMapper;
import com.platform.dao.ApiUserMapper;
import com.platform.entity.ArticleVo;
import com.platform.entity.FollowVo;
import com.platform.entity.PointLogVo;
import com.platform.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ApiArticleService {
    @Autowired
    private ApiArticleMapper articleDao;
    @Autowired
    private ApiFollowMapper followDao;
    @Autowired
    private ApiUserMapper userDao;
    @Autowired
    private ApiPointLogMapper pointLogDao;

    private final static Integer point = 5;

    public ArticleVo queryObject(Long id) {
        return articleDao.queryObject(id);
    }


    public List<ArticleVo> queryList(Map<String, Object> map) {
        return articleDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return articleDao.queryTotal(map);
    }


    public void save(ArticleVo articleVo) {
        articleDao.save(articleVo);
    }


    public void update(ArticleVo articleVo) {
        articleDao.update(articleVo);
    }


    public void delete(Long id) {
        articleDao.delete(id);
    }


    public void deleteBatch(Long[] ids) {
        articleDao.deleteBatch(ids);
    }



    @Transient
    public void cancelZan(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleGoodCnt = articleVo.getArticleGoodCnt() - 1 ;
        articleVo.setArticleGoodCnt(articleGoodCnt);
        articleVo.setArticleUpdateTime(new Date());

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
    }

    @Transient
    public void zan(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleGoodCnt = articleVo.getArticleGoodCnt() + 1 ;
        articleVo.setArticleGoodCnt(articleGoodCnt);
        articleVo.setArticleLatestCmtTime(new Date());

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(articleVo.getoId());
        followVo.setFollowingType(4);

        articleDao.update(articleVo);
        followDao.save(followVo);
    }


    @Transient
    public void cancelWatch(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleGoodCnt = articleVo.getArticleWatchCnt() - 1 ;
        articleVo.setArticleWatchCnt(articleGoodCnt);
        articleVo.setArticleUpdateTime(new Date());

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
    }

    @Transient
    public void watch(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleGoodCnt = articleVo.getArticleWatchCnt() + 1 ;
        articleVo.setArticleWatchCnt(articleGoodCnt);
        articleVo.setArticleLatestCmtTime(new Date());

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(articleVo.getoId());
        followVo.setFollowingType(3);

        articleDao.update(articleVo);
        followDao.save(followVo);
    }

    @Transient
    public void collect(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleCollectCnt = articleVo.getArticleCollectCnt() + 1;
        articleVo.setArticleCollectCnt(articleCollectCnt);
        articleVo.setArticleLatestCmtTime(new Date());

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(articleVo.getoId());
        followVo.setFollowingType(2);

        articleDao.update(articleVo);
        followDao.save(followVo);
    }

    @Transient
    public void cancelCollect(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleCollectCnt = articleVo.getArticleCollectCnt() - 1 ;
        articleVo.setArticleCollectCnt(articleCollectCnt);
        articleVo.setArticleUpdateTime(new Date());

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
    }

    @Transient
    public void saveAndUpdate(ArticleVo articleVo){

        UserVo userVo = userDao.queryObject(articleVo.getArticleAuthorId());
        Integer userPoint = userVo.getUserPoint() + point;
        Integer userArticleCount = userVo.getUserArticleCount() + 1;
        userVo.setUserPoint(userPoint);
        userVo.setUserArticleCount(userArticleCount);
        userVo.setUserLatestArticleTime(articleVo.getArticleCreateTime());
        userVo.setUserUpdateTime(articleVo.getArticleCreateTime());

        articleDao.save(articleVo);
        userDao.update(userVo);

        PointLogVo pointLogVo = new PointLogVo();
        pointLogVo.setPointLogArticleAuthorId(articleVo.getArticleAuthorId());
        pointLogVo.setPointLogArticleId(articleVo.getoId());
        pointLogVo.setPointLogType(0);
        pointLogVo.setPointLogPoint(point);
        pointLogVo.setPointLogCreateTime(articleVo.getArticleCreateTime());
        pointLogDao.save(pointLogVo);
    }

}
