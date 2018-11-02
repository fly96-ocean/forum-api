package com.platform.service;

import com.platform.dao.*;
import com.platform.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final static Integer point = 5;

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
    public void good(CommentVo commentVo) {
        Integer commentGoodCnt = commentVo.getCommentGoodCnt() + 1;
        commentVo.setCommentGoodCnt(commentGoodCnt);
        commentDao.update(commentVo);
    }

    @Transactional
    public void bad(CommentVo commentVo) {
        Integer commentBadCnt = commentVo.getCommentBadCnt() + 1;
        commentVo.setCommentBadCnt(commentBadCnt);
        commentDao.update(commentVo);
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

        commentDao.save(commentVo);
        userDao.update(userVo);

        PointLogVo pointLogVo = new PointLogVo();
        pointLogVo.setPointLogArticleAuthorId(commentVo.getCommentAuthorId());
        pointLogVo.setPointLogArticleId(commentVo.getCommentOnArticleId());
        pointLogVo.setPointLogType(1);
        pointLogVo.setPointLogPoint(point);
        pointLogVo.setPointLogCreateTime(commentVo.getCommentCreateTime());
        pointLogDao.save(pointLogVo);

        ArticleVo articleVo = articleDao.queryObject(commentVo.getCommentOnArticleId());
        articleVo.setArticleCommentCount(articleVo.getArticleCommentCount()+1);
        articleDao.update(articleVo);
    }

}
