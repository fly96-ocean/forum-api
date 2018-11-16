package com.platform.api;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.platform.entity.CommentVo;
import com.platform.entity.PointLogVo;
import com.platform.entity.ReportVo;
import com.platform.service.ApiCommentService;
import com.platform.service.ApiPointLogService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import com.platform.validator.Assert;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "积分")
@RestController
@RequestMapping("/api/point")
public class ApiPointController extends ApiBaseAction {

    @Autowired
    private ApiPointLogService pointService;

    @RequestMapping("/logs")
    public R list() {
        Map<String, Object> map = new HashMap<>();

        if(getUserId() != null){
            map.put("pointLogArticleAuthorId", getUserId());
        }
        List<PointLogVo> pointLogs = pointService.queryList(map);

        return R.ok().put("msg", pointLogs);
    }

}