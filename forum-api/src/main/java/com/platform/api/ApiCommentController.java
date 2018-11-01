package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.entity.CommentVo;
import com.platform.entity.PointLogVo;
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

    private ApiUserService userService;

    private ApiPointLogService pointLogService;

    private final static Integer point = 5;

    @RequestMapping("/list")
    public R list(Long articleId) {

        Assert.isNull(articleId, "帖子ID不能为空！");
        Map<String, Object> map = new HashMap<>();
        map.put("domainType", articleId);
        List<CommentVo> adVoList = commentService.queryList(map);

        return R.ok().put("msg", adVoList);
    }

    @RequestMapping("/save")
    @PostMapping("save")
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
            commentVo.setCommentIP(jsonObject.getString("commentIP"));
            commentVo.setCommentUA(jsonObject.getString("commentUA"));
            commentVo.setCommentAnonymous(jsonObject.getInteger("commentAnonymous"));
            commentVo.setCommentGoodCnt(jsonObject.getInteger("commentGoodCnt"));
            commentVo.setCommentBadCnt(jsonObject.getInteger("commentBadCnt"));
            commentVo.setCommentScore(jsonObject.getDouble("commentScore"));
            commentVo.setCommentReplyCnt(jsonObject.getInteger("commentReplyCnt"));
            commentVo.setCommentAudioURL(jsonObject.getString("commentAudioURL"));
            commentVo.setCommentQnAOffered(jsonObject.getInteger("commentQnAOffered"));
            commentVo.setCommentVisible(jsonObject.getInteger("commentQnAOffered"));

            UserVo userVo = userService.queryObject(currentUserId);
            Integer userPoint = userVo.getUserPoint() + point;
            Integer userCommentCount = userVo.getUserCommentCount() + 1;
            userVo.setUserPoint(userPoint);
            userVo.setUserCommentCount(userCommentCount);

            userService.update(userVo);
            commentService.save(commentVo);

            PointLogVo pointLogVo = new PointLogVo();
            pointLogVo.setPointLogArticleAuthorId(jsonObject.getLong("userId"));
            pointLogVo.setPointLogArticleId(commentVo.getCommentOnArticleId());
            pointLogVo.setPointLogType(1);
            pointLogVo.setPointLogPoint(point);
            pointLogVo.setPointLogCreateTime(commentVo.getCommentCreateTime());
            pointLogService.save(pointLogVo);

            return R.ok().put("msg", "评论保存成功！");
        }

        return R.ok().put("msg", "评论保存失败！");
    }

}