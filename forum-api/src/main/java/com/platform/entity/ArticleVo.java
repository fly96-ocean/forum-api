package com.platform.entity;

import com.platform.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long oId;
    //帖子标题
    private String articleTitle;
    //帖子表情，英文状态逗号分隔
    private String articleTags;
    //帖子标签
    private List<String> articleTagsList;
    //分类  id
    private Long articleDomainId;
    //帖子作者 id
    private Long articleAuthorId;
    //帖子回帖计数
    private Long articleCommentCount;
    //帖子浏览计数
    private Long articleViewCount;
    //帖子正文内容
    private String articleContent;
    //帖子打赏区内容
    private String articleRewardContent;
    //帖子打赏积分
    private Integer articleRewardPoint;
    //帖子访问路径
    private String articlePermalink;
    //帖子创建时间
    private Date articleCreateTime;
    //帖子更新时间
    private Date articleUpdateTime;
    //帖子最新回帖时间
    private Date articleLatestCmtTime;
    //帖子最新回帖者用户名
    private String articleLatestCmterName;
    //帖子随机数，用于快速选择随机帖子    （可以暂时不考虑）
    private Double articleRandomDouble;
    //帖子是否可回帖 0 可以评论   1 不可以评论
    private Integer articleCommentable;
    //0：CodeMirror-Markdown      （默认都是0）
    private Integer articleEditorType;
    //0：正常，1：删除，2：锁定  3：封禁
    private Integer articleStatus;
    //0：普通帖子，1：视频帖子，2：问答
    private Integer articleType;
    //帖子点赞计数
    private Integer articleGoodCnt;
    //帖子点踩计数
    private Integer articleBadCnt;
    //帖子收藏计数
    private Integer articleCollectCnt;
    //帖子关注计数
    private Integer articleWatchCnt;
    //帖子被举报次数
    private Integer articleReportCnt;
    //帖子 Reddit 算法评分    （可以暂时不考虑）
    private Double redditScore;
    //发帖 IP 所在城市
    private String articleCity;
    //发帖 IP
    private String articleIP;
    //User-Agent
    private String articleUA;
    //帖子置顶时间
    private Date articleStick;
    //帖子是否置顶  0：未置顶   1： 置顶
    private Integer isStick;
    //0：公开，1：匿名
    private Integer articleAnonymous;
    //0：非优选，1：优选
    private Integer articlePerfect;
    //0：允许不登录浏览， 1：登录才可浏览    （默认都是1）
    private Integer articleAnonymousView;
    //帖子语音文件 URL
    private String articleAudioURL;
    //问答悬赏积分（仅作用于问答帖）
    private Integer articleQnAOfferPoint;
    //问答贴 是否已解决问题  0：已解决  1：待处理
    private Integer articleAskIsSolved;
    //周/月邮件推送优先级    （可以暂时不考虑）
    private Integer articlePushOrder;
    //帖子1图地址    （视频帖子只有一个封面）
    private String articleImg1URL;
    //帖子2图地址
    private String articleImg2URL;
    //帖子3图地址
    private String articleImg3URL;
    //帖子4图地址
    private String articleImg4URL;
    //帖子5图地址
    private String articleImg5URL;
    //帖子6图地址
    private String articleImg6URL;
    //发帖人昵称
    private String userNickName;
    //发帖人头像
    private String userAvatarURL;
    //当前用户id
    private Long currentUserId;
    //是否被赞过
    private Integer hasGood;
    //是否被关注
    private Integer hasWatchedArticle;
    //是否关注帖子作者
    private Integer hasWatchedAuthor;
    //是否被收藏
    private Integer hasCollect;
    //帖子封面图
    private List<String> articleImgs;




    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public List<String> getArticleTagsList() {
        articleTagsList = new ArrayList<>();
        if(StringUtils.isNotEmpty(this.getArticleTags())){
            String[] at = this.getArticleTags().split(",");
            for(String tag:at){
                articleTagsList.add(tag);
            }
        }
        return articleTagsList;
    }

    public Long getArticleDomainId() {
        return articleDomainId;
    }

    public void setArticleDomainId(Long articleDomainId) {
        this.articleDomainId = articleDomainId;
    }

    public Long getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(Long articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    public Long getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Long articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Long getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Long articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleRewardContent() {
        return articleRewardContent;
    }

    public void setArticleRewardContent(String articleRewardContent) {
        this.articleRewardContent = articleRewardContent;
    }

    public Integer getArticleRewardPoint() {
        return articleRewardPoint;
    }

    public void setArticleRewardPoint(Integer articleRewardPoint) {
        this.articleRewardPoint = articleRewardPoint;
    }

    public String getArticlePermalink() {
        return articlePermalink;
    }

    public void setArticlePermalink(String articlePermalink) {
        this.articlePermalink = articlePermalink;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public Date getArticleLatestCmtTime() {
        return articleLatestCmtTime;
    }

    public void setArticleLatestCmtTime(Date articleLatestCmtTime) {
        this.articleLatestCmtTime = articleLatestCmtTime;
    }

    public String getArticleLatestCmterName() {
        return articleLatestCmterName;
    }

    public void setArticleLatestCmterName(String articleLatestCmterName) {
        this.articleLatestCmterName = articleLatestCmterName;
    }

    public Double getArticleRandomDouble() {
        return articleRandomDouble;
    }

    public void setArticleRandomDouble(Double articleRandomDouble) {
        this.articleRandomDouble = articleRandomDouble;
    }

    public Integer getArticleCommentable() {
        return articleCommentable;
    }

    public void setArticleCommentable(Integer articleCommentable) {
        this.articleCommentable = articleCommentable;
    }

    public Integer getArticleEditorType() {
        return articleEditorType;
    }

    public void setArticleEditorType(Integer articleEditorType) {
        this.articleEditorType = articleEditorType;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Integer getArticleGoodCnt() {
        return articleGoodCnt;
    }

    public void setArticleGoodCnt(Integer articleGoodCnt) {
        this.articleGoodCnt = articleGoodCnt;
    }

    public Integer getArticleBadCnt() {
        return articleBadCnt;
    }

    public void setArticleBadCnt(Integer articleBadCnt) {
        this.articleBadCnt = articleBadCnt;
    }

    public Integer getArticleCollectCnt() {
        return articleCollectCnt;
    }

    public void setArticleCollectCnt(Integer articleCollectCnt) {
        this.articleCollectCnt = articleCollectCnt;
    }

    public Integer getArticleWatchCnt() {
        return articleWatchCnt;
    }

    public void setArticleWatchCnt(Integer articleWatchCnt) {
        this.articleWatchCnt = articleWatchCnt;
    }

    public Double getRedditScore() {
        return redditScore;
    }

    public void setRedditScore(Double redditScore) {
        this.redditScore = redditScore;
    }

    public String getArticleCity() {
        return articleCity;
    }

    public void setArticleCity(String articleCity) {
        this.articleCity = articleCity;
    }

    public String getArticleIP() {
        return articleIP;
    }

    public void setArticleIP(String articleIP) {
        this.articleIP = articleIP;
    }

    public String getArticleUA() {
        return articleUA;
    }

    public void setArticleUA(String articleUA) {
        this.articleUA = articleUA;
    }

    public Integer getIsStick() {
        return isStick;
    }

    public void setIsStick(Integer isStick) {
        this.isStick = isStick;
    }

    public Date getArticleStick() {
        return articleStick;
    }

    public void setArticleStick(Date articleStick) {
        this.articleStick = articleStick;
    }

    public Integer getArticleAnonymous() {
        return articleAnonymous;
    }

    public void setArticleAnonymous(Integer articleAnonymous) {
        this.articleAnonymous = articleAnonymous;
    }

    public Integer getArticlePerfect() {
        return articlePerfect;
    }

    public void setArticlePerfect(Integer articlePerfect) {
        this.articlePerfect = articlePerfect;
    }

    public Integer getArticleAnonymousView() {
        return articleAnonymousView;
    }

    public void setArticleAnonymousView(Integer articleAnonymousView) {
        this.articleAnonymousView = articleAnonymousView;
    }

    public String getArticleAudioURL() {
        return articleAudioURL;
    }

    public void setArticleAudioURL(String articleAudioURL) {
        this.articleAudioURL = articleAudioURL;
    }

    public Integer getArticleQnAOfferPoint() {
        return articleQnAOfferPoint;
    }

    public void setArticleQnAOfferPoint(Integer articleQnAOfferPoint) {
        this.articleQnAOfferPoint = articleQnAOfferPoint;
    }

    public Integer getArticlePushOrder() {
        return articlePushOrder;
    }

    public void setArticlePushOrder(Integer articlePushOrder) {
        this.articlePushOrder = articlePushOrder;
    }

    public String getArticleImg1URL() {
        return articleImg1URL;
    }

    public void setArticleImg1URL(String articleImg1URL) {
        this.articleImg1URL = articleImg1URL;
    }

    public String getArticleImg2URL() {
        return articleImg2URL;
    }

    public void setArticleImg2URL(String articleImg2URL) {
        this.articleImg2URL = articleImg2URL;
    }

    public String getArticleImg3URL() {
        return articleImg3URL;
    }

    public void setArticleImg3URL(String articleImg3URL) {
        this.articleImg3URL = articleImg3URL;
    }

    public String getArticleImg4URL() {
        return articleImg4URL;
    }

    public void setArticleImg4URL(String articleImg4URL) {
        this.articleImg4URL = articleImg4URL;
    }

    public String getArticleImg5URL() {
        return articleImg5URL;
    }

    public void setArticleImg5URL(String articleImg5URL) {
        this.articleImg5URL = articleImg5URL;
    }

    public String getArticleImg6URL() {
        return articleImg6URL;
    }

    public void setArticleImg6URL(String articleImg6URL) {
        this.articleImg6URL = articleImg6URL;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Integer getHasGood() {
        return hasGood;
    }

    public void setHasGood(Integer hasGood) {
        this.hasGood = hasGood;
    }

    public Integer getHasWatchedArticle() {
        return hasWatchedArticle;
    }

    public void setHasWatchedArticle(Integer hasWatchedArticle) {
        this.hasWatchedArticle = hasWatchedArticle;
    }

    public Integer getHasWatchedAuthor() {
        return hasWatchedAuthor;
    }

    public void setHasWatchedAuthor(Integer hasWatchedAuthor) {
        this.hasWatchedAuthor = hasWatchedAuthor;
    }

    public Integer getHasCollect() {
        return hasCollect;
    }

    public void setHasCollect(Integer hasCollect) {
        this.hasCollect = hasCollect;
    }

    public List<String> getArticleImgs() {
        articleImgs = new ArrayList<>();
        if(StringUtils.isNotEmpty(this.getArticleImg1URL())){
            articleImgs.add(this.getArticleImg1URL());
        }
        if(StringUtils.isNotEmpty(this.getArticleImg2URL())){
            articleImgs.add(this.getArticleImg2URL());
        }
        if(StringUtils.isNotEmpty(this.getArticleImg3URL())){
            articleImgs.add(this.getArticleImg3URL());
        }
        if(StringUtils.isNotEmpty(this.getArticleImg4URL())){
            articleImgs.add(this.getArticleImg4URL());
        }
        if(StringUtils.isNotEmpty(this.getArticleImg5URL())){
            articleImgs.add(this.getArticleImg5URL());
        }
        if(StringUtils.isNotEmpty(this.getArticleImg6URL())){
            articleImgs.add(this.getArticleImg6URL());
        }
        return articleImgs;
    }

    public Integer getArticleReportCnt() {
        return articleReportCnt;
    }

    public void setArticleReportCnt(Integer articleReportCnt) {
        this.articleReportCnt = articleReportCnt;
    }

    public Integer getArticleAskIsSolved() {
        return articleAskIsSolved;
    }

    public void setArticleAskIsSolved(Integer articleAskIsSolved) {
        this.articleAskIsSolved = articleAskIsSolved;
    }
}
