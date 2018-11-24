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
public class ApiCommentService {

    @Autowired
    private ApiCommentMapper commentDao;
    @Autowired
    private ApiUserMapper userDao;
    @Autowired
    private ApiPointLogMapper pointLogDao;
    @Autowired
    private ApiArticleMapper articleDao;
    @Autowired
    private ApiReportMapper reportDao;
    @Autowired
    private ApiFollowMapper followDao;


    private final static Integer point = 0;

    public CommentVo queryObject(Long id) {
        return commentDao.queryObject(id);
    }


    public List<CommentVo> queryList(Map<String, Object> map) {
        return commentDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return commentDao.queryTotal(map);
    }


    public void save(CommentVo commentVo) {
        commentDao.save(commentVo);
    }


    public void update(CommentVo commentVo) {
        commentDao.update(commentVo);
    }


    public void delete(Integer id) {
        commentDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        commentDao.deleteBatch(ids);
    }

    @Transactional
    public void good(CommentVo commentVo, Long userId) {
        Integer commentGoodCnt = commentVo.getCommentGoodCnt() + 1;
        commentVo.setCommentGoodCnt(commentGoodCnt);
        commentDao.update(commentVo);

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(commentVo.getoId());
        followVo.setFollowingType(7);
        followDao.save(followVo);
    }

    @Transactional
    public void cancelGood(CommentVo commentVo, Long userId) {
        Integer commentGoodCnt = commentVo.getCommentGoodCnt() - 1;
        commentVo.setCommentGoodCnt(commentGoodCnt);
        commentDao.update(commentVo);
        followDao.deleteByFollowerIdAndFollowingIdAndFollowingType(userId, commentVo.getoId(),4);
    }

    @Transactional
    public void bad(CommentVo commentVo, Long userId) {
        Integer commentBadCnt = commentVo.getCommentBadCnt() + 1;
        commentVo.setCommentBadCnt(commentBadCnt);
        commentDao.update(commentVo);

        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(userId);
        followVo.setFollowingId(commentVo.getoId());
        followVo.setFollowingType(8);
        followDao.save(followVo);
    }

    @Transactional
    public void saveReportComment(ReportVo reportVo) {
        reportDao.save(reportVo);
    }

    @Transactional
    public void saveAndUpdate(CommentVo commentVo){

        UserVo userVo = userDao.queryObject(commentVo.getCommentAuthorId());
        Integer userPoint = userVo.getUserPoint() + point;
        Integer userCommentCount = userVo.getUserCommentCount() + 1;
        userVo.setUserPoint(userPoint);
        userVo.setUserCommentCount(userCommentCount);
        userDao.update(userVo);
        commentDao.save(commentVo);
        Long originalCommentId = commentVo.getCommentOriginalCommentId();
        if(originalCommentId != null && originalCommentId != 0){
            CommentVo originalComment = commentDao.queryObject(originalCommentId);
            originalComment.setCommentReplyCnt(originalComment.getCommentReplyCnt()+1);
            commentDao.update(originalComment);
        }

        if(point > 0){
            PointLogVo pointLogVo = new PointLogVo();
            pointLogVo.setPointLogArticleAuthorId(commentVo.getCommentAuthorId());
            pointLogVo.setPointLogArticleId(commentVo.getCommentOnArticleId());
            pointLogVo.setPointLogType(1);
            pointLogVo.setPointLogPoint(point);
            pointLogVo.setPointLogCreateTime(new Date());
            pointLogDao.save(pointLogVo);
        }

        ArticleVo articleVo = articleDao.queryObject(commentVo.getCommentOnArticleId());
        articleVo.setArticleCommentCount(articleVo.getArticleCommentCount()+1);
        articleDao.update(articleVo);
    }

    @Transactional
    public void offeredAndUpdate(CommentVo commentVo) {
        UserVo commentUserVo = userDao.queryObject(commentVo.getCommentAuthorId());

        ArticleVo articleVo = articleDao.queryObject(commentVo.getCommentOnArticleId());

        UserVo articleUserVo = userDao.queryObject(articleVo.getArticleAuthorId());

        Integer articleQnAOfferPoint = articleVo.getArticleQnAOfferPoint();

        Integer articleUserPoint = articleUserVo.getUserPoint() - articleQnAOfferPoint;

        Integer articleUserPlatformScore = articleUserVo.getUserPlatformScore() - articleQnAOfferPoint;

        Integer articleUserUsedPoint = articleUserVo.getUserUsedPoint() + articleQnAOfferPoint;



        Integer commentUserPoint = commentUserVo.getUserPoint() + articleQnAOfferPoint;

        Integer commentUserPlatformScore = articleUserVo.getUserPlatformScore() + articleQnAOfferPoint;



        commentUserVo.setUserPoint(commentUserPoint);
        commentUserVo.setUserPlatformScore(commentUserPlatformScore);

        articleUserVo.setUserPoint(articleUserPoint);

        articleUserVo.setUserUsedPoint(articleUserUsedPoint);

        articleUserVo.setUserPlatformScore(articleUserPlatformScore);


        userDao.update(commentUserVo);
        userDao.update(articleUserVo);
        commentDao.update(commentVo);
    }

}
