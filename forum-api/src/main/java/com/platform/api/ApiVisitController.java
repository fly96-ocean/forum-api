package com.platform.api;

import com.platform.entity.ArticleVo;
import com.platform.entity.FollowVo;
import com.platform.entity.PointLogVo;
import com.platform.entity.UserVo;
import com.platform.service.ApiArticleService;
import com.platform.service.ApiPointLogService;
import com.platform.service.ApiUserService;
import com.platform.service.ApiVisitService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import com.platform.validator.Assert;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "访问记录表")
@RestController
@RequestMapping("/api/visit")
public class ApiVisitController extends ApiBaseAction {

    @Autowired
    private ApiVisitService visitService;

    @RequestMapping("/")
    public R cancelFollow(Long userId) {
//        Assert.isNull(userId, "关注用户Id不能为空!");
//        Long followerId = getUserId();
//        FollowVo followVo = new FollowVo();
//        followVo.setFollowerId(followerId);
//        followVo.setFollowingId(userId);
//        followVo.setFollowingType(0);
//        userService.cancelFollow(followerId, userId);

        return R.ok().put("msg", "取消关注用户成功！");
    }

}