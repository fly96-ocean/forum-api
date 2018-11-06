package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import com.platform.validator.Assert;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "帖子")
@RestController
@RequestMapping("/api/article")
public class ApiArticleController extends ApiBaseAction {

    @Autowired
    private ApiArticleService articleService;
    @Autowired
    private ApiFollowService followService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiPointLogService pointLogService;
    @Autowired
    private ApiTagService tagService;

    private final static Integer point = 5;

    @RequestMapping("/newList")
    public R newList(Long articleType) {
        Assert.isNull(articleType, "帖子类型不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("articleType", articleType);
        if(getUserId() != null){
            map.put("currentUserId", getUserId());
        }
        List<ArticleVo> articleVoList = articleService.queryList(map);

        return R.ok().put("msg", articleVoList);
    }

    @RequestMapping("/domainArticleList")
    public R domainArticleList(Long articleType, Long domainId) {
        Assert.isNull(articleType, "帖子类型不能为空");
        Assert.isNull(domainId, "帖子分类类型不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("articleType", articleType);
        map.put("domainId", domainId);
        if(getUserId() != null){
            map.put("currentUserId", getUserId());
        }
        List<ArticleVo> articleVoList = articleService.queryList(map);

        return R.ok().put("msg", articleVoList);
    }

    /**
     * 点赞
     * @param articleId
     * @return
     */
    @RequestMapping("/good")
    public R good(Long articleId) {
        Assert.isNull(articleId, "帖子ID不能为空");
        Long userId = getUserId();
        articleService.zan(articleId, userId);
        return R.ok().put("msg", "帖子好评已更新");
    }

    /**
     * 取消点赞
     * @param articleId
     * @return
     */
    @RequestMapping("/cancelGood")
    public R cancelGood(Long articleId) {
        Assert.isNull(articleId, "帖子ID不能为空");

        Long userId = getUserId();
        articleService.cancelZan(articleId, userId);

        return R.ok().put("msg", "帖子好评取消");
    }

    /**
     * 差评
     * 暂时用不到此接口
     * @param articleId
     * @return
     */
//    @RequestMapping("/bad")
//    public R bad(Integer articleId) {
//        Assert.isNull(articleId, "帖子ID不能为空");
//        Long userId = getUserId();
//
//        ArticleVo articleVo = articleService.queryObject(articleId);
//        Integer articleBadCnt = articleVo.getArticleBadCnt() + 1;
//        articleVo.setArticleGoodCnt(articleBadCnt);
//        articleVo.setArticleLatestCmtTime(new Date());
//
//        FollowVo followVo = new FollowVo();
//        followVo.setFollowerId(userId);
//        followVo.setFollowingId(articleVo.getoId());
//        followVo.setFollowingType(5);
//
//        articleService.update(articleVo);
//        followService.save(followVo);
//
//        return R.ok().put("msg", "帖子差评已更新");
//    }

    @RequestMapping(value = "/collect")
    public R collect(Long articleId) {
        Assert.isNull(articleId, "帖子ID不能为空");
        Long userId = getUserId();
        articleService.collect(articleId, userId);

        return R.ok().put("msg", "帖子收藏已更新");
    }

    @RequestMapping(value = "/cancelCollect")
    public R cancelCollect(Long articleId) {
        Assert.isNull(articleId, "帖子ID不能为空");
        Long userId = getUserId();
        articleService.cancelCollect(articleId, userId);

        return R.ok().put("msg", "帖子收藏已更新");
    }

    @RequestMapping("/watch")
    public R watch(Long articleId) {
        Assert.isNull(articleId, "帖子ID不能为空");
        Long userId = getUserId();
        articleService.watch(articleId, userId);

        return R.ok().put("msg", "帖子关注已更新");
    }

    @RequestMapping("/cancelWatch")
    public R cancelWatch(Long articleId) {
        Assert.isNull(articleId, "帖子ID不能为空");
        Long userId = getUserId();
        articleService.cancelWatch(articleId, userId);

        return R.ok().put("msg", "帖子关注已更新");
    }

    @RequestMapping("/detail")
    public R detail(Long articleId) {
        Assert.isNull(articleId, "帖子ID不能为空");
        ArticleVo articleVo = articleService.queryObject(articleId);

        return R.ok().put("msg", articleVo);
    }

    @RequestMapping("/save")
    public R save() {
        JSONObject jsonObject = super.getJsonRequest();
        if(null != jsonObject) {
            ArticleVo articleVo = new ArticleVo();
            articleVo.setArticleTitle(jsonObject.getString("articleTitle"));
            articleVo.setArticleContent(jsonObject.getString("articleContent"));
            if(jsonObject.getLong("tagId") != null){
                Long tagId = jsonObject.getLong("tagId");
                TagVo tag = tagService.queryObject(tagId);
                articleVo.setArticleTags(tag.getTagTitle());
            }

            articleVo.setArticleCommentable(jsonObject.getInteger("articleCommentable"));
            articleVo.setArticleEditorType(jsonObject.getInteger("articleEditorType"));
            articleVo.setArticleType(jsonObject.getInteger("articleType"));
            articleVo.setArticleAnonymous(jsonObject.getInteger("articleAnonymous"));
            articleVo.setArticleAudioURL(jsonObject.getString("articleAudioURL"));
            articleVo.setArticlePushOrder(jsonObject.getInteger("articlePushOrder"));
            articleVo.setArticleImg1URL(jsonObject.getString("articleImg1URL"));
            articleVo.setArticleImg2URL(jsonObject.getString("articleImg2URL"));
            articleVo.setArticleImg3URL(jsonObject.getString("articleImg3URL"));
            articleVo.setArticleImg4URL(jsonObject.getString("articleImg4URL"));
            articleVo.setArticleImg5URL(jsonObject.getString("articleImg5URL"));
            articleVo.setArticleImg6URL(jsonObject.getString("articleImg6URL"));
            articleVo.setArticleAuthorId(this.getUserId());
//            articleVo.setArticleDomainId(jsonObject.getLong("domainId"));
            articleVo.setArticleCreateTime(new Date());

            articleService.saveAndUpdate(articleVo);

            return R.ok().put("msg", articleVo);
        }
        return R.error().put("msg", "帖子保存失败！");
    }

    @RequestMapping("/update")
    public R update() {
        JSONObject jsonObject = super.getJsonRequest();
        if(null != jsonObject) {
            Long oId = jsonObject.getLong("articleId");
            ArticleVo articleVo = articleService.queryObject(oId);
            if(jsonObject.getLong("articleDomainId") != null){
                articleVo.setArticleDomainId(jsonObject.getLong("articleDomainId"));
            }
            if(jsonObject.getInteger("articleAnonymous") != null){
                articleVo.setArticleAnonymous(jsonObject.getInteger("articleAnonymous"));
            }
            if(jsonObject.getInteger("articleCommentable") != null){
                articleVo.setArticleCommentable(jsonObject.getInteger("articleCommentable"));
            }


            articleService.update(articleVo);

            return R.ok().put("msg", "帖子保存成功！");
        }
        return R.ok().put("msg", "帖子保存失败！");
    }

}