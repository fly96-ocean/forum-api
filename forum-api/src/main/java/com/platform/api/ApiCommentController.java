package com.platform.api;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.platform.entity.*;
import com.platform.service.ApiArticleService;
import com.platform.service.ApiCommentService;
import com.platform.service.ApiPointLogService;
import com.platform.service.ApiUserService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import com.platform.validator.Assert;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "评论")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController extends ApiBaseAction {

    @Autowired
    private ApiCommentService commentService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiArticleService articleService;

    @RequestMapping("/list")
    public R list(Long articleId) {

        Assert.isNull(articleId, "帖子ID不能为空！");
        Map<String, Object> map = new HashMap<>();
        map.put("commentOnArticleId", articleId);
        if(getUserId() != null){
            map.put("currentUserId", getUserId());
        }
        map.put("commentOriginalCommentId", 0);
        List<CommentVo> commentVoList = commentService.queryList(map);

        return R.ok().put("msg", commentVoList);
    }


    @RequestMapping("/subCommentList")
    public R subCommentList(Long commentId) {

        Assert.isNull(commentId, "评论ID不能为空！");
        Map<String, Object> map = new HashMap<>();
        map.put("commentOriginalCommentId", commentId);

        List<CommentVo> commentVoList = commentService.queryList(map);

        return R.ok().put("msg", commentVoList);
    }

    @RequestMapping("/detail")
    public R detail(Long commentId) {
        Assert.isNull(commentId, "评论ID不能为空！");
        CommentVo commentVo = commentService.queryObject(commentId);

        return R.ok().put("msg", commentVo);
    }

    @RequestMapping("/save")
    public R save() {
        JSONObject jsonObject = super.getJsonRequest();
        if(null != jsonObject) {
            Long currentUserId = this.getUserId();

            CommentVo commentVo = new CommentVo();
            commentVo.setCommentContent(jsonObject.getString("commentContent"));
            commentVo.setCommentCreateTime(new Date());
            commentVo.setCommentAuthorId(currentUserId);
            commentVo.setCommentOnArticleId(jsonObject.getLong("articleId"));
            commentVo.setCommentSharpURL(jsonObject.getString("commentSharpURL"));
            if(jsonObject.getLong("commentOriginalCommentId") != null){
                commentVo.setCommentOriginalCommentId(jsonObject.getLong("commentOriginalCommentId"));
            }else{
                commentVo.setCommentOriginalCommentId(0L);
            }

            commentVo.setCommentStatus(1);
            commentVo.setCommentIP(this.getClientIp());
            commentVo.setCommentUA(this.getClientUA());
            if(jsonObject.getInteger("commentAnonymous") == null){
                commentVo.setCommentAnonymous(1);
            } else {
                commentVo.setCommentAnonymous(jsonObject.getInteger("commentAnonymous"));
            }

            if(jsonObject.getInteger("commentGoodCnt") == null){
                commentVo.setCommentGoodCnt(0);
            } else {
                commentVo.setCommentGoodCnt(jsonObject.getInteger("commentGoodCnt"));
            }
            if(jsonObject.getInteger("commentBadCnt") == null){
                commentVo.setCommentBadCnt(0);
            } else {
                commentVo.setCommentBadCnt(jsonObject.getInteger("commentBadCnt"));
            }
            if(jsonObject.getDouble("commentScore") == null){
                commentVo.setCommentScore(0.0);
            } else {
                commentVo.setCommentScore(jsonObject.getDouble("commentBadCnt"));
            }

            if(jsonObject.getInteger("commentReplyCnt") == null){
                commentVo.setCommentReplyCnt(0);
            } else {
                commentVo.setCommentReplyCnt(jsonObject.getInteger("commentReplyCnt"));
            }

            commentVo.setCommentAudioURL(jsonObject.getString("commentAudioURL"));
            commentVo.setCommentQnAOffered(jsonObject.getInteger("commentQnAOffered"));
            commentVo.setCommentVisible(jsonObject.getInteger("commentQnAOffered"));

            commentService.saveAndUpdate(commentVo);

            return R.ok().put("msg", "评论保存成功！");
        }

        return R.ok().put("msg", "评论保存失败！");
    }

    @RequestMapping("/good")
    public R good(Long commentId) {
        Assert.isNull(commentId, "回帖ID不能为空");
        CommentVo commentVo = commentService.queryObject(commentId);
        Long userId = this.getUserId();
        commentService.good(commentVo, userId);
        return R.ok().put("msg", "");
    }

    @RequestMapping("/cancelGood")
    public R cancelGood(Long commentId) {
        Assert.isNull(commentId, "回帖ID不能为空");
        CommentVo commentVo = commentService.queryObject(commentId);
        Long userId = this.getUserId();
        commentService.cancelGood(commentVo, userId);
        return R.ok().put("msg", "");
    }

    @RequestMapping("/bad")
    public R bad(Long commentId) {
        Assert.isNull(commentId, "回帖ID不能为空");
        Long userId = this.getUserId();
        CommentVo commentVo = commentService.queryObject(commentId);
        commentService.bad(commentVo, userId);
        return R.ok().put("msg", "");
    }

    @RequestMapping("/report")
    public R report(Long commentId, String reportTypes) {
        Assert.isNull(commentId, "回帖ID不能为空");
        Assert.isNull(reportTypes, "举报reportType不能为空");
        String reportMemo = reportTypes;
        ReportVo reportVo = new ReportVo();

        reportVo.setReportUserId(getUserId());
        reportVo.setReportDataId(commentId);
        if(StringUtils.equals(reportTypes, "垃圾广告")){
            reportVo.setReportDataType(0);
        } else if(StringUtils.equals(reportTypes, "色情低俗")){
            reportVo.setReportDataType(1);
        } else if(StringUtils.equals(reportTypes, "违法违规")){
            reportVo.setReportDataType(2);
        } else if(StringUtils.equals(reportTypes, "涉嫌侵权")){
            reportVo.setReportDataType(3);
        } else if(StringUtils.equals(reportTypes, "人身攻击")){
            reportVo.setReportDataType(4);
        } else if(StringUtils.equals(reportTypes, "冒充账号")){
            reportVo.setReportDataType(5);
        } else if(StringUtils.equals(reportTypes, "垃圾广告账号")){
            reportVo.setReportDataType(6);
        } else if(StringUtils.equals(reportTypes, "个人信息违规")){
            reportVo.setReportDataType(7);
        }

        reportVo.setReportType(1);
        reportVo.setReportMemo(reportMemo);
        reportVo.setReportHandled(0);

        commentService.saveReportComment(reportVo);
        return R.ok().put("msg", "");
    }

    @RequestMapping("/replyList")
    public R replyList(Long commentOriginalCommentId) {
        Assert.isNull(commentOriginalCommentId, "父回帖 id不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("commentOriginalCommentId", commentOriginalCommentId);
        List<CommentVo> commentVoList = commentService.queryList(map);

        return R.ok().put("msg", commentVoList);
    }

    @RequestMapping("/offered")
    public R offered(Long commentId) {
        CommentVo commentVo = commentService.queryObject(commentId);
        if(commentVo.getCommentQnAOffered() == 1) {
            return R.ok().put("msg", "不能重复操作，回帖已经被采纳！");
        } else {
            commentVo.setCommentQnAOffered(1);
            commentService.offeredAndUpdate(commentVo);

            UserVo commentUser = userService.queryObject(commentVo.getCommentAuthorId());

            ArticleVo articleVo = articleService.queryObject(commentVo.getCommentOnArticleId());

            UserVo articleUser = userService.queryObject(articleVo.getArticleAuthorId());

            String commentLoginUser = commentUser.getUserLoginId();

            this.updateUserScore(commentLoginUser, articleVo.getArticleQnAOfferPoint(), "回帖采纳时，回帖人增加积分");

            this.updateUserScore(articleUser.getUserLoginId(), -articleVo.getArticleQnAOfferPoint(), "回帖采纳时，发帖人减少积分");

            return R.ok().put("msg", "回帖已经被采纳！");
        }
    }

}