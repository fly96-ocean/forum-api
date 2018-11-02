package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.entity.CommentVo;
import com.platform.entity.PointLogVo;
import com.platform.entity.ReportVo;
import com.platform.entity.UserVo;
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

    private final static Integer point = 5;

    @RequestMapping("/list")
    public R list(Long articleId) {

        Assert.isNull(articleId, "帖子ID不能为空！");
        Map<String, Object> map = new HashMap<>();
        map.put("commentOnArticleId", articleId);
        List<CommentVo> commentVoList = commentService.queryList(map);

        return R.ok().put("msg", commentVoList);
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
            commentVo.setCommentOriginalCommentId(jsonObject.getLong("commentOriginalCommentId"));
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
        commentService.good(commentVo);
        return R.ok().put("msg", "");
    }

    @RequestMapping("/bad")
    public R bad(Long commentId) {
        Assert.isNull(commentId, "回帖ID不能为空");
        CommentVo commentVo = commentService.queryObject(commentId);
        commentService.bad(commentVo);
        return R.ok().put("msg", "");
    }

    @RequestMapping("/report")
    public R report(Long commentId, Integer reportType, String reportMemo) {
        Assert.isNull(commentId, "回帖ID不能为空");
        Assert.isNull(reportType, "举报reportType不能为空");

        ReportVo reportVo = new ReportVo();

        reportVo.setReportUserId(getUserId());
        reportVo.setReportDataId(commentId);
        reportVo.setReportDataType(1);
        reportVo.setReportType(reportType);
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

}