package com.platform.service;

import com.platform.dao.*;
import com.platform.entity.*;
import com.platform.utils.ResourceUtil;
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

    String point = ResourceUtil.getConfigByName("publish.article.point");

    public ArticleVo queryObject(Long id) {
        return articleDao.queryObject(id);
    }


    public List<ArticleVo> queryList(Map<String, Object> map) {
        return articleDao.queryList(map);
    }

    public List<ArticleVo> queryMyCommentArticle(Map<String, Object> map){
        return articleDao.queryMyCommentArticle(map);
    }

    public List<ArticleVo> queryMyCollectArticle(Map<String, Object> map){
        return articleDao.queryMyCollectArticle(map);
    }
    public List<ArticleVo> queryMyZanArticle(Map<String, Object> map){
        return articleDao.queryMyZanArticle(map);
    }
    public List<ArticleVo> queryMyVisitArticle(Map<String, Object> map){
        return articleDao.queryMyVisitArticle(map);
    }

    public List<ArticleVo> queryPerfectArticle(){
        return articleDao.queryPerfectArticle();
    }


    public int queryTotal(Map<String, Object> map) {
        return articleDao.queryTotal(map);
    }


    public void save(ArticleVo articleVo) {
        /***
         * 1、保存帖子
         * 2、update积分
         * 3、保存积分记录
         * 4、如果是打赏，需要扣除积分
         */


        articleDao.save(articleVo);
    }


    public void update(ArticleVo articleVo) {
        articleDao.update(articleVo);
    }


    public void delete(Long id) {
        ArticleVo articleVo = articleDao.queryObject(id);
        articleVo.setArticleStatus(1);
        articleDao.update(articleVo);
    }


    public void deleteBatch(Long[] ids) {
        articleDao.deleteBatch(ids);
    }


    @Transactional
    public ArticleVo queryDetail(Long articleId, Long userId){

        ArticleVo articleVo = articleDao.queryObject(articleId);

        articleVo.setArticleViewCount(articleVo.getArticleViewCount()+1);

        articleDao.update(articleVo);

        Date currentDate = new Date();
        VisitVo visitVo = new VisitVo();
        visitVo.setVisitUserId(userId);
        visitVo.setVisitType(1);
        visitVo.setVisitedId(articleVo.getoId());
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");
        visitDao.save(visitVo);

        return articleVo;
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

//        VisitVo visitVo = new VisitVo();
//        visitVo.setVisitUserId(userId);
//        visitVo.setVisitType(1);
//        visitVo.setVisitedId(articleId);
//        visitVo.setVisitCreated(currentDate);
//        visitVo.setVisitExpired(currentDate);
//        visitVo.setVisitCity("");
//        visitVo.setVisitDeviceId("");
//        visitVo.setVisitIP("");
//        visitVo.setVisitUA("");
//        visitVo.setVisitURL("");
//        visitVo.setVisitRefererURL("");

        UserVo userVo = userDao.queryObject(userId);
        userVo.setUserGoodCount(userVo.getUserGoodCount()-1);

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
//        visitDao.save(visitVo);
        userDao.update(userVo);
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
        visitVo.setVisitType(11);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        UserVo userVo = userDao.queryObject(userId);
        userVo.setUserGoodCount(userVo.getUserGoodCount()+1);

        articleDao.update(articleVo);
        followDao.save(followVo);
        visitDao.save(visitVo);
        userDao.update(userVo);
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

//        VisitVo visitVo = new VisitVo();
//        visitVo.setVisitUserId(userId);
//        visitVo.setVisitType(1);
//        visitVo.setVisitedId(articleId);
//        visitVo.setVisitCreated(currentDate);
//        visitVo.setVisitExpired(currentDate);
//        visitVo.setVisitCity("");
//        visitVo.setVisitDeviceId("");
//        visitVo.setVisitIP("");
//        visitVo.setVisitUA("");
//        visitVo.setVisitURL("");
//        visitVo.setVisitRefererURL("");

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
//        visitDao.save(visitVo);
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
        visitVo.setVisitType(14);
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
        visitVo.setVisitType(12);
        visitVo.setVisitedId(articleId);
        visitVo.setVisitCreated(currentDate);
        visitVo.setVisitExpired(currentDate);
        visitVo.setVisitCity("");
        visitVo.setVisitDeviceId("");
        visitVo.setVisitIP("");
        visitVo.setVisitUA("");
        visitVo.setVisitURL("");
        visitVo.setVisitRefererURL("");

        UserVo userVo = userDao.queryObject(userId);
        userVo.setUserCollectCount(userVo.getUserCollectCount()+1);

        articleDao.update(articleVo);
        followDao.save(followVo);
        visitDao.save(visitVo);
        userDao.update(userVo);
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

//        VisitVo visitVo = new VisitVo();
//        visitVo.setVisitUserId(userId);
//        visitVo.setVisitType(1);
//        visitVo.setVisitedId(articleId);
//        visitVo.setVisitCreated(currentDate);
//        visitVo.setVisitExpired(currentDate);
//        visitVo.setVisitCity("");
//        visitVo.setVisitDeviceId("");
//        visitVo.setVisitIP("");
//        visitVo.setVisitUA("");
//        visitVo.setVisitURL("");
//        visitVo.setVisitRefererURL("");

        UserVo userVo = userDao.queryObject(userId);
        userVo.setUserCollectCount(userVo.getUserCollectCount()-1);

        articleDao.update(articleVo);
        followDao.deleteByArticleIdAndUserId(articleId, userId);
//        visitDao.save(visitVo);
        userDao.update(userVo);
    }

    @Transactional
    public void saveAndUpdate(ArticleVo articleVo, UserVo userVo, PointLogVo pointLogVo){

        articleDao.save(articleVo);
        userDao.update(userVo);

        pointLogVo.setPointLogArticleId(articleVo.getoId());
        pointLogDao.save(pointLogVo);
    }




    /****服务器端接口 ****/
    public List<ArticleVo> serverQueryList(Map<String, Object> map){
        return articleDao.serverQueryList(map);
    }

    @Transactional
    public void deleteOrNot(Long[] ids, Integer status){
        for(int i = 0; i<ids.length; i++){
            if(ids[i]!=null){
                ArticleVo articleVo = articleDao.queryObject(ids[i]);
                articleVo.setArticleStatus(status);
                articleDao.update(articleVo);
            }

        }
    }

    public int serverQueryTotal(Map<String, Object> map){
        return articleDao.serverQueryTotal(map);
    }

    @Transactional
    public void setPerfectOrNot(Long[] ids, Integer isPerfect){
        for(int i = 0; i<ids.length; i++){
            if(ids[i]!=null){
                ArticleVo articleVo = articleDao.queryObject(ids[i]);
                articleVo.setArticlePerfect(isPerfect);
                articleDao.update(articleVo);
            }

        }
    }

    @Transactional
    public void stickOrNot(Long[] ids, Integer isStick){
        for(int i = 0; i<ids.length; i++){
            if(ids[i]!=null){
                ArticleVo articleVo = articleDao.queryObject(ids[i]);
                articleVo.setIsStick(isStick);
                articleDao.update(articleVo);
            }

        }
    }

}
