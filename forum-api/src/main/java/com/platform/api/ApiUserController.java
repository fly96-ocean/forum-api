package com.platform.api;

import com.platform.entity.*;
import com.platform.service.ApiArticleService;
import com.platform.service.ApiFollowService;
import com.platform.service.ApiPointLogService;
import com.platform.service.ApiUserService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import com.platform.validator.Assert;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "个人中心")
@RestController
@RequestMapping("/api/user")
public class ApiUserController extends ApiBaseAction {

    @Autowired
    private ApiUserService userService;

    @Autowired
    private ApiArticleService articleService;

    @Autowired
    private ApiPointLogService pointLogService;

    @RequestMapping("/userInfo")
    public R userInfo() {
        Long userId = getUserId();
        Assert.isNull(userId, "用户不能为空！");
        UserVo userVo = userService.queryObject(userId);

        return R.ok().put("msg", userVo);
    }

    @RequestMapping("/userFans")
    public R userFans() {
        Long userId = getUserId();
        Assert.isNull(userId, "用户不能为空！");
        List<UserVo> userVoList = userService.queryListByUserFans(userId);

        return R.ok().put("msg", userVoList);
    }

    @RequestMapping("/userFriends")
    public R userFriends() {
        Long userId = getUserId();
        Assert.isNull(userId, "用户不能为空！");
        List<UserVo> userVoList = userService.queryListByUserFriends(userId);

        return R.ok().put("msg", userVoList);
    }

    @RequestMapping("/userFollow")
    public R userFollow(){
        Long userId = getUserId();
        List<UserVo> userVoList = userService.queryListUserFollow(userId);

        return R.ok().put("msg", userVoList);
    }

    @RequestMapping("/userPublish")
    public R userPublish() {
        Long userId = getUserId();
        Assert.isNull(userId, "用户不能为空！");

        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);

        List<ArticleVo> articleVoList = articleService.queryList(map);

        return R.ok().put("msg", articleVoList);
    }

    @RequestMapping("/userPoints")
    public R userPoints() {
        Long userId = getUserId();
        Assert.isNull(userId, "用户不能为空！");

        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);

        List<PointLogVo> pointLogVoList = pointLogService.queryList(map);

        return R.ok().put("msg", pointLogVoList);
    }

    @RequestMapping("/follow")
    public R follow(Long userId) {
        Assert.isNull(userId, "关注用户Id不能为空!");
        Long followerId = getUserId();
        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(followerId);
        followVo.setFollowingId(userId);
        followVo.setFollowingType(0);
        userService.follow(followVo);

        return R.ok().put("msg", "关注用户成功！");
    }

    @RequestMapping("/cancelFollow")
    public R cancelFollow(Long userId) {
        Assert.isNull(userId, "关注用户Id不能为空!");
        Long followerId = getUserId();
        FollowVo followVo = new FollowVo();
        followVo.setFollowerId(followerId);
        followVo.setFollowingId(userId);
        followVo.setFollowingType(0);
        userService.cancelFollow(followerId, userId);

        return R.ok().put("msg", "取消关注用户成功！");
    }

}