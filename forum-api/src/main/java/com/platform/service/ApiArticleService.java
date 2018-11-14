package com.platform.service;

import com.platform.dao.*;
import com.platform.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ApiVisitMapper visitDao;

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



    @Transactional
    public void cancelZan(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);

        Date currentDate = new Date();
        Long articleViewCount = articleVo.getArticleViewCount() + 1;
        articleVo.setArticleViewCount(articleViewCount);

        Integer articleGoodCnt = articleVo.getArticleGoodCnt() - 1 ;
        articleVo.setArticleGoodCnt(articleGoodCnt);
        articleVo.setArticleUpdateTime(currentDate);

        VisitVo visitVo = new VisitVo();
        visitVo.setVisitUserId(userId);
        visitVo.setVisitType(1);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
        visitDao.save(visitVo);
    }

    @Transactional
    public void zan(Long articleId, Long userId){
        Date currentDate = new Date();
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleGoodCnt = articleVo.getArticleGoodCnt() + 1 ;
        Long articleViewCount = articleVo.getArticleViewCount() + 1;
        articleVo.setArticleGoodCnt(articleGoodCnt);
        articleVo.setArticleLatestCmtTime(currentDate);
        articleVo.setArticleViewCount(articleViewCount);

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(articleVo.getoId());
        followVo.setFollowingType(4);

        VisitVo visitVo = new VisitVo();
        visitVo.setVisitUserId(userId);
        visitVo.setVisitType(1);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        articleDao.update(articleVo);
        followDao.save(followVo);
        visitDao.save(visitVo);
    }


    @Transactional
    public void cancelWatch(Long articleId, Long userId){
        Date currentDate = new Date();
        ArticleVo articleVo = articleDao.queryObject(articleId);
        Integer articleGoodCnt = articleVo.getArticleWatchCnt() - 1 ;
        Long articleViewCount = articleVo.getArticleViewCount() + 1;
        articleVo.setArticleWatchCnt(articleGoodCnt);
        articleVo.setArticleUpdateTime(currentDate);
        articleVo.setArticleViewCount(articleViewCount);

        VisitVo visitVo = new VisitVo();
        visitVo.setVisitUserId(userId);
        visitVo.setVisitType(1);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
        visitDao.save(visitVo);
    }

    @Transactional
    public void watch(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);

        Date currentDate = new Date();
        Long articleViewCount = articleVo.getArticleViewCount() + 1;
        articleVo.setArticleViewCount(articleViewCount);

        Integer articleGoodCnt = articleVo.getArticleWatchCnt() + 1 ;
        articleVo.setArticleWatchCnt(articleGoodCnt);
        articleVo.setArticleLatestCmtTime(currentDate);

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(articleVo.getoId());
        followVo.setFollowingType(3);


        VisitVo visitVo = new VisitVo();
        visitVo.setVisitUserId(userId);
        visitVo.setVisitType(1);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        articleDao.update(articleVo);
        followDao.save(followVo);
        visitDao.save(visitVo);
    }

    @Transactional
    public void collect(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);

        Date currentDate = new Date();
        Long articleViewCount = articleVo.getArticleViewCount() + 1;
        articleVo.setArticleViewCount(articleViewCount);

        Integer articleCollectCnt = articleVo.getArticleCollectCnt() + 1;
        articleVo.setArticleCollectCnt(articleCollectCnt);
        articleVo.setArticleLatestCmtTime(currentDate);

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(articleVo.getoId());
        followVo.setFollowingType(2);

        VisitVo visitVo = new VisitVo();
        visitVo.setVisitUserId(userId);
        visitVo.setVisitType(1);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        articleDao.update(articleVo);
        followDao.save(followVo);
        visitDao.save(visitVo);
    }

    @Transactional
    public void cancelCollect(Long articleId, Long userId){
        ArticleVo articleVo = articleDao.queryObject(articleId);

        Date currentDate = new Date();
        Long articleViewCount = articleVo.getArticleViewCount() + 1;
        articleVo.setArticleViewCount(articleViewCount);

        Integer articleCollectCnt = articleVo.getArticleCollectCnt() - 1 ;
        articleVo.setArticleCollectCnt(articleCollectCnt);
        articleVo.setArticleUpdateTime(currentDate);

        VisitVo visitVo = new VisitVo();
        visitVo.setVisitUserId(userId);
        visitVo.setVisitType(1);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
        visitDao.save(visitVo);
    }

    @Transactional
    public void saveAndUpdate(ArticleVo articleVo, UserVo userVo){

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
