package com.platform.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import com.platform.utils.ResourceUtil;
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
    private ApiTagService tagService;
    @Autowired
    private ApiUserService userService;

    String point = ResourceUtil.getConfigByName("publish.article.point");

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

    @RequestMapping("/myNewList")
    public R myNewList(Long articleType) {
        Assert.isNull(articleType, "帖子类型不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("articleType", articleType);
        if(getUserId() != null){
            map.put("articleAuthorId", getUserId());
        }
        List<ArticleVo> articleVoList = articleService.queryList(map);
        return R.ok().put("msg", articleVoList);
    }

    @RequestMapping("/myCommentArticleList")
    public R myCommentArticleList(Long articleType) {
        Assert.isNull(articleType, "帖子类型不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("articleType", articleType);
        if(getUserId() != null){
            map.put("commentAuthorId", getUserId());
        }
        List<ArticleVo> articleVoList = articleService.queryMyCommentArticle(map);
        return R.ok().put("msg", articleVoList);
    }

    @RequestMapping("/myCollectArticleList")
    public R myCollectArticleList(Long articleType) {
        Assert.isNull(articleType, "帖子类型不能为空");
        Map<String, Object> map = new HashMap<>();
        map.put("articleType", articleType);
        if(getUserId() != null){
            map.put("followerId", getUserId());
        }
        List<ArticleVo> articleVoList = articleService.queryMyCollectArticle(map);
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
        Long userId = getUserId();
        ArticleVo articleVo = articleService.queryDetail(articleId, userId);

        return R.ok().put("msg", articleVo);
    }

    @RequestMapping("/save")
    public R save() {
        JSONObject jsonObject = super.getJsonRequest();
        if(null != jsonObject) {
            ArticleVo articleVo = new ArticleVo();
            articleVo.setArticleTitle(jsonObject.getString("articleTitle"));
            articleVo.setArticleContent(jsonObject.getString("articleContent"));
            JSONArray articleImages = jsonObject.getJSONArray("articleImages");
            if(articleImages != null){
                for(int i = 0; i<articleImages.size(); i++){
                    if(articleImages.get(i) != null){
                        if(i==0){
                            articleVo.setArticleImg1URL(articleImages.get(i).toString());
                        }
                        if(i==1){
                            articleVo.setArticleImg2URL(articleImages.get(i).toString());
                        }
                        if(i==2){
                            articleVo.setArticleImg3URL(articleImages.get(i).toString());
                        }
                        if(i==3){
                            articleVo.setArticleImg4URL(articleImages.get(i).toString());
                        }
                        if(i==4){
                            articleVo.setArticleImg5URL(articleImages.get(i).toString());
                        }
                        if(i==5){
                            articleVo.setArticleImg6URL(articleImages.get(i).toString());
                        }
                    }

                }
            }

            if(jsonObject.getLong("tagId") != null){
                Long tagId = jsonObject.getLong("tagId");
                TagVo tag = tagService.queryObject(tagId);
                articleVo.setArticleTags(tag.getTagTitle());
            }

            Integer articleQnAOfferPoint = jsonObject.getInteger("articleQnAOfferPoint");
            Integer articleRewardPoint = jsonObject.getInteger("articleRewardPoint");
            String articleRewardContent = jsonObject.getString("articleRewardContent");

            articleVo.setArticleCommentable(jsonObject.getInteger("articleCommentable"));
            articleVo.setArticleEditorType(jsonObject.getInteger("articleEditorType"));
            articleVo.setArticleType(jsonObject.getInteger("articleType"));
            articleVo.setArticleAnonymous(jsonObject.getInteger("articleAnonymous"));
            articleVo.setArticleAudioURL(jsonObject.getString("articleAudioURL"));
            articleVo.setArticlePushOrder(jsonObject.getInteger("articlePushOrder"));
            articleVo.setArticleAuthorId(this.getUserId());
//            articleVo.setArticleDomainId(jsonObject.getLong("domainId"));
            articleVo.setArticleCreateTime(new Date());

            if(articleRewardPoint != null) {
                articleVo.setArticleRewardPoint(articleRewardPoint);
            } else {
                articleVo.setArticleRewardPoint(0);
            }

            if(articleRewardContent != null) {
                articleVo.setArticleRewardContent(articleRewardContent);
            } else {
                articleVo.setArticleRewardContent(null);
            }

            UserVo userVo = userService.queryObject(this.getUserId());
            Integer userPoint = userVo.getUserPoint();
            Integer userArticleCount = userVo.getUserArticleCount() + 1;
            userVo.setUserPoint(userPoint+Integer.parseInt(point));
            userVo.setUserArticleCount(userArticleCount);
            userVo.setUserLatestArticleTime(articleVo.getArticleCreateTime());
            userVo.setUserUpdateTime(articleVo.getArticleCreateTime());

            if(articleQnAOfferPoint != null) {
                if(userPoint < articleQnAOfferPoint) {
                    return R.error().put("msg", "用户积分不足，请重新选择悬赏积分！");
                } else {
                    articleVo.setArticleQnAOfferPoint(articleQnAOfferPoint);
                }
            } else {
                articleVo.setArticleQnAOfferPoint(0);
            }

            articleService.saveAndUpdate(articleVo, userVo);

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

    //To Do
    @RequestMapping("/updateViewCount")
    public R updateViewCount() {
        return R.ok();
    }
}