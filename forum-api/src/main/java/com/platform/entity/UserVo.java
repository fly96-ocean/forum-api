package com.platform.entity;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;
import java.util.Date;

public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer oId;
    //用户编号
    private String userNo;
    //用户邮箱
    private String userEmail;
    //用户名
    private String userName;
    //密码
    private String userPassword;
    //昵称
    private String userNickname;
    //用户自评标签
    private String userTags;
    //用户链接
    private String userURL;
    //qq
    private String userQQ;
    //用户简介
    private String userIntro;
    //0：Gravatar，1：外链
    private Integer userAvatarType;
    //头像图片 URL
    private String userAvatarURL;
    //用户是否在线
    private Integer userOnlineFlag;
    //用户角色
    private String userRole;
    //发帖计数
    private Integer userArticleCount;
    //回帖计数
    private Integer userCommentCount;
    //用户标签计数
    private Integer userTagCount;
    //用户粉丝计数
    private Integer userFansCount;
    //用户被赞计数（我发表的帖子被赞，我的评论被赞，均统计在此）
    private Integer userGoodCount;
    //我收藏帖子数
    private Integer userCollectCount;

    //0：正常，1：封禁
    private Integer userStatus;
    //积分余额
    private Integer userPoint;
    //累计消费积分
    private Integer userUsedPoint;
    //0：参与积分余额排行榜，1：不参与积分余额排行榜
    private Integer userJoinPointRank;
    //0：参与积分消费排行榜，1：不参与积分消费排行榜
    private Integer userJoinUsedPointRank;
    //最近一次发帖时间
    private Date userLatestArticleTime;
    //最近一次回帖时间
    private Date userLatestCmtTime;
    //最近一次登录时间
    private Date userLatestLoginTime;
    //最近一次登录 IP
    private String userLatestLoginIP;
    //0：应用角色 - 黑客，1：应用角色 - 画家
    private Integer userAppRole;
    //最近一次签到时间
    private Date userCheckinTime;
    //最长连续签到开始日期
    private Integer userLongestCheckinStreakStart;
    //最长连续签到结束日期
    private Integer userLongestCheckinStreakEnd;
    //当前连续签到开始日期
    private Integer userCurrentCheckinStreakStart;
    //当前连续签到结束日期
    private Integer userCurrentCheckinStreakEnd;
    //最长连续签到天数
    private Integer userLongestCheckinStreak;
    //当前连续签到天数
    private Integer userCurrentCheckinStreak;
    //用户皮肤
    private String userSkin;
    //用户移动端皮肤
    private String userMobileSkin;
    //用户所在国家
    private String userCountry;
    //用户所在省份
    private String userProvince;
    //用户所在城市
    private String userCity;
    //用户更新时间
    private Date userUpdateTime;
    //0：公开地理位置，1：不公开地理位置
    private Integer userGeoStatus;
    //0：传统回帖浏览模式（按时间升序），1：实时回帖浏览模式（按时间降序）
    private Integer userCommentViewMode;
    //0：公开 User-Agent，1：不公开 User-Agent
    private Integer userUAStatus;
    //0：启用 Web 通知，1：禁用 Web 通知
    private Integer userNotifyStatus;
    //0：公开积分列表，1：不公开积分列表
    private Integer userPointStatus;
    //0：公开清风明月列表，1：不公开清风明月列表
    private Integer userBreezemoonStatus;
    //0：公开关注者列表，1：不公开关注者列表
    private Integer userFollowerStatus;
    //0：公开收藏帖子列表，1：不公开收藏帖子列表
    private Integer userFollowingArticleStatus;
    //0：公开关注帖子列表，1：不公开关注帖子列表
    private Integer userWatchingArticleStatus;
    //0：公开关注标签列表，1：不公开关注标签列表
    private Integer userFollowingTagStatus;
    //0：公开关注用户列表，1：不公开关注用户列表
    private Integer userFollowingUserStatus;
    //0：公开回帖列表，1：不公开回帖列表
    private Integer userCommentStatus;
    //0：公开帖子列表，1：不公开帖子列表
    private Integer userArticleStatus;
    //0：公开在线状态，1：不公开在线状态
    private Integer userOnlineStatus;
    //帖子列表每页显示条数
    private Integer userListPageSize;
    //0：仅标题，1：标题+摘要
    private Integer userListViewMode;
    //0：原始头像图片，比如 gif 动图，1：静态头像图片
    private Integer userAvatarViewMode;
    //0：启用邮件订阅，1：禁用邮件订阅
    private Integer userSubMailStatus;
    //用户订阅邮件最近推送时间
    private String userSubMailSendTime;
    //0：启用键盘快捷键，1：禁用键盘快捷键
    private Integer userKeyboardShortcutsStatus;
    //0：回帖后自动关注帖子，1：回帖后不自动关注帖子
    private Integer userReplyWatchArticleStatus;
    //用户所选语言
    private String userLanguage;
    //用户所在时区
    private String userTimezone;
    //新注册后向导到达第几步
    private Integer userGuideStep;
    //0：使用重定向页面，1：禁用重定向页面
    private Integer userForwardPageStatus;
    //等级
    private Integer level;
    //等级描述
    private String levelIntro;

    //用于更新用户积分
    private Integer userScore;
    //用户所在门店ID
    private String userShopId;
    //用户所在门店Name
    private String userShopName;
    //用户所在经销商ID
    private String userDistributorId;
    //用户所在经销商Name
    private String userDistributorName;
    //用户登陆ID
    private String userLoginId;
    //用户积分平台积分
    private Integer userPlatformScore;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserTags() {
        return userTags;
    }

    public void setUserTags(String userTags) {
        this.userTags = userTags;
    }

    public String getUserURL() {
        return userURL;
    }

    public void setUserURL(String userURL) {
        this.userURL = userURL;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public Integer getUserAvatarType() {
        return userAvatarType;
    }

    public void setUserAvatarType(Integer userAvatarType) {
        this.userAvatarType = userAvatarType;
    }

    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }

    public Integer getUserOnlineFlag() {
        return userOnlineFlag;
    }

    public void setUserOnlineFlag(Integer userOnlineFlag) {
        this.userOnlineFlag = userOnlineFlag;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Integer getUserArticleCount() {
        return userArticleCount;
    }

    public void setUserArticleCount(Integer userArticleCount) {
        this.userArticleCount = userArticleCount;
    }

    public Integer getUserCommentCount() {
        return userCommentCount;
    }

    public void setUserCommentCount(Integer userCommentCount) {
        this.userCommentCount = userCommentCount;
    }

    public Integer getUserTagCount() {
        return userTagCount;
    }

    public void setUserTagCount(Integer userTagCount) {
        this.userTagCount = userTagCount;
    }

    public Integer getUserFansCount() {
        return userFansCount;
    }

    public void setUserFansCount(Integer userFansCount) {
        this.userFansCount = userFansCount;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public Integer getUserUsedPoint() {
        return userUsedPoint;
    }

    public void setUserUsedPoint(Integer userUsedPoint) {
        this.userUsedPoint = userUsedPoint;
    }

    public Integer getUserJoinPointRank() {
        return userJoinPointRank;
    }

    public void setUserJoinPointRank(Integer userJoinPointRank) {
        this.userJoinPointRank = userJoinPointRank;
    }

    public Integer getUserJoinUsedPointRank() {
        return userJoinUsedPointRank;
    }

    public void setUserJoinUsedPointRank(Integer userJoinUsedPointRank) {
        this.userJoinUsedPointRank = userJoinUsedPointRank;
    }

    public Date getUserLatestArticleTime() {
        return userLatestArticleTime;
    }

    public void setUserLatestArticleTime(Date userLatestArticleTime) {
        this.userLatestArticleTime = userLatestArticleTime;
    }

    public Date getUserLatestCmtTime() {
        return userLatestCmtTime;
    }

    public void setUserLatestCmtTime(Date userLatestCmtTime) {
        this.userLatestCmtTime = userLatestCmtTime;
    }

    public Date getUserLatestLoginTime() {
        return userLatestLoginTime;
    }

    public void setUserLatestLoginTime(Date userLatestLoginTime) {
        this.userLatestLoginTime = userLatestLoginTime;
    }

    public String getUserLatestLoginIP() {
        return userLatestLoginIP;
    }

    public void setUserLatestLoginIP(String userLatestLoginIP) {
        this.userLatestLoginIP = userLatestLoginIP;
    }

    public Integer getUserAppRole() {
        return userAppRole;
    }

    public void setUserAppRole(Integer userAppRole) {
        this.userAppRole = userAppRole;
    }

    public Date getUserCheckinTime() {
        return userCheckinTime;
    }

    public void setUserCheckinTime(Date userCheckinTime) {
        this.userCheckinTime = userCheckinTime;
    }

    public Integer getUserLongestCheckinStreakStart() {
        return userLongestCheckinStreakStart;
    }

    public void setUserLongestCheckinStreakStart(Integer userLongestCheckinStreakStart) {
        this.userLongestCheckinStreakStart = userLongestCheckinStreakStart;
    }

    public Integer getUserLongestCheckinStreakEnd() {
        return userLongestCheckinStreakEnd;
    }

    public void setUserLongestCheckinStreakEnd(Integer userLongestCheckinStreakEnd) {
        this.userLongestCheckinStreakEnd = userLongestCheckinStreakEnd;
    }

    public Integer getUserCurrentCheckinStreakStart() {
        return userCurrentCheckinStreakStart;
    }

    public void setUserCurrentCheckinStreakStart(Integer userCurrentCheckinStreakStart) {
        this.userCurrentCheckinStreakStart = userCurrentCheckinStreakStart;
    }

    public Integer getUserCurrentCheckinStreakEnd() {
        return userCurrentCheckinStreakEnd;
    }

    public void setUserCurrentCheckinStreakEnd(Integer userCurrentCheckinStreakEnd) {
        this.userCurrentCheckinStreakEnd = userCurrentCheckinStreakEnd;
    }

    public Integer getUserLongestCheckinStreak() {
        return userLongestCheckinStreak;
    }

    public void setUserLongestCheckinStreak(Integer userLongestCheckinStreak) {
        this.userLongestCheckinStreak = userLongestCheckinStreak;
    }

    public Integer getUserCurrentCheckinStreak() {
        return userCurrentCheckinStreak;
    }

    public void setUserCurrentCheckinStreak(Integer userCurrentCheckinStreak) {
        this.userCurrentCheckinStreak = userCurrentCheckinStreak;
    }

    public String getUserSkin() {
        return userSkin;
    }

    public void setUserSkin(String userSkin) {
        this.userSkin = userSkin;
    }

    public String getUserMobileSkin() {
        return userMobileSkin;
    }

    public void setUserMobileSkin(String userMobileSkin) {
        this.userMobileSkin = userMobileSkin;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public Integer getUserGeoStatus() {
        return userGeoStatus;
    }

    public void setUserGeoStatus(Integer userGeoStatus) {
        this.userGeoStatus = userGeoStatus;
    }

    public Integer getUserCommentViewMode() {
        return userCommentViewMode;
    }

    public void setUserCommentViewMode(Integer userCommentViewMode) {
        this.userCommentViewMode = userCommentViewMode;
    }

    public Integer getUserUAStatus() {
        return userUAStatus;
    }

    public void setUserUAStatus(Integer userUAStatus) {
        this.userUAStatus = userUAStatus;
    }

    public Integer getUserNotifyStatus() {
        return userNotifyStatus;
    }

    public void setUserNotifyStatus(Integer userNotifyStatus) {
        this.userNotifyStatus = userNotifyStatus;
    }

    public Integer getUserPointStatus() {
        return userPointStatus;
    }

    public void setUserPointStatus(Integer userPointStatus) {
        this.userPointStatus = userPointStatus;
    }

    public Integer getUserBreezemoonStatus() {
        return userBreezemoonStatus;
    }

    public void setUserBreezemoonStatus(Integer userBreezemoonStatus) {
        this.userBreezemoonStatus = userBreezemoonStatus;
    }

    public Integer getUserFollowerStatus() {
        return userFollowerStatus;
    }

    public void setUserFollowerStatus(Integer userFollowerStatus) {
        this.userFollowerStatus = userFollowerStatus;
    }

    public Integer getUserFollowingArticleStatus() {
        return userFollowingArticleStatus;
    }

    public void setUserFollowingArticleStatus(Integer userFollowingArticleStatus) {
        this.userFollowingArticleStatus = userFollowingArticleStatus;
    }

    public Integer getUserWatchingArticleStatus() {
        return userWatchingArticleStatus;
    }

    public void setUserWatchingArticleStatus(Integer userWatchingArticleStatus) {
        this.userWatchingArticleStatus = userWatchingArticleStatus;
    }

    public Integer getUserFollowingTagStatus() {
        return userFollowingTagStatus;
    }

    public void setUserFollowingTagStatus(Integer userFollowingTagStatus) {
        this.userFollowingTagStatus = userFollowingTagStatus;
    }

    public Integer getUserFollowingUserStatus() {
        return userFollowingUserStatus;
    }

    public void setUserFollowingUserStatus(Integer userFollowingUserStatus) {
        this.userFollowingUserStatus = userFollowingUserStatus;
    }

    public Integer getUserCommentStatus() {
        return userCommentStatus;
    }

    public void setUserCommentStatus(Integer userCommentStatus) {
        this.userCommentStatus = userCommentStatus;
    }

    public Integer getUserArticleStatus() {
        return userArticleStatus;
    }

    public void setUserArticleStatus(Integer userArticleStatus) {
        this.userArticleStatus = userArticleStatus;
    }

    public Integer getUserOnlineStatus() {
        return userOnlineStatus;
    }

    public void setUserOnlineStatus(Integer userOnlineStatus) {
        this.userOnlineStatus = userOnlineStatus;
    }

    public Integer getUserListPageSize() {
        return userListPageSize;
    }

    public void setUserListPageSize(Integer userListPageSize) {
        this.userListPageSize = userListPageSize;
    }

    public Integer getUserListViewMode() {
        return userListViewMode;
    }

    public void setUserListViewMode(Integer userListViewMode) {
        this.userListViewMode = userListViewMode;
    }

    public Integer getUserAvatarViewMode() {
        return userAvatarViewMode;
    }

    public void setUserAvatarViewMode(Integer userAvatarViewMode) {
        this.userAvatarViewMode = userAvatarViewMode;
    }

    public Integer getUserSubMailStatus() {
        return userSubMailStatus;
    }

    public void setUserSubMailStatus(Integer userSubMailStatus) {
        this.userSubMailStatus = userSubMailStatus;
    }

    public String getUserSubMailSendTime() {
        return userSubMailSendTime;
    }

    public void setUserSubMailSendTime(String userSubMailSendTime) {
        this.userSubMailSendTime = userSubMailSendTime;
    }

    public Integer getUserKeyboardShortcutsStatus() {
        return userKeyboardShortcutsStatus;
    }

    public void setUserKeyboardShortcutsStatus(Integer userKeyboardShortcutsStatus) {
        this.userKeyboardShortcutsStatus = userKeyboardShortcutsStatus;
    }

    public Integer getUserReplyWatchArticleStatus() {
        return userReplyWatchArticleStatus;
    }

    public void setUserReplyWatchArticleStatus(Integer userReplyWatchArticleStatus) {
        this.userReplyWatchArticleStatus = userReplyWatchArticleStatus;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getUserTimezone() {
        return userTimezone;
    }

    public void setUserTimezone(String userTimezone) {
        this.userTimezone = userTimezone;
    }

    public Integer getUserGuideStep() {
        return userGuideStep;
    }

    public void setUserGuideStep(Integer userGuideStep) {
        this.userGuideStep = userGuideStep;
    }

    public Integer getUserForwardPageStatus() {
        return userForwardPageStatus;
    }

    public void setUserForwardPageStatus(Integer userForwardPageStatus) {
        this.userForwardPageStatus = userForwardPageStatus;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelIntro() {
        if(this.level == 0){
            levelIntro = "新手";
        }
        if(this.level == 1){
            levelIntro = "试用";
        }

        return levelIntro;
    }

    public void setLevelIntro(String levelIntro) {
        this.levelIntro = levelIntro;
    }

    public Integer getUserGoodCount() {
        return userGoodCount;
    }

    public void setUserGoodCount(Integer userGoodCount) {
        this.userGoodCount = userGoodCount;
    }

    public Integer getUserCollectCount() {
        return userCollectCount;
    }

    public void setUserCollectCount(Integer userCollectCount) {
        this.userCollectCount = userCollectCount;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public String getUserShopId() {
        return userShopId;
    }

    public void setUserShopId(String userShopId) {
        this.userShopId = userShopId;
    }

    public String getUserShopName() {
        return userShopName;
    }

    public void setUserShopName(String userShopName) {
        this.userShopName = userShopName;
    }

    public String getUserDistributorId() {
        return userDistributorId;
    }

    public void setUserDistributorId(String userDistributorId) {
        this.userDistributorId = userDistributorId;
    }

    public String getUserDistributorName() {
        return userDistributorName;
    }

    public void setUserDistributorName(String userDistributorName) {
        this.userDistributorName = userDistributorName;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public Integer getUserPlatformScore() {
        return userPlatformScore;
    }

    public void setUserPlatformScore(Integer userPlatformScore) {
        this.userPlatformScore = userPlatformScore;
    }
}
