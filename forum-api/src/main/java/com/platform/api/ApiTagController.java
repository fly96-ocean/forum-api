package com.platform.api;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.platform.entity.CommentVo;
import com.platform.entity.ReportVo;
import com.platform.entity.TagVo;
import com.platform.service.ApiCommentService;
import com.platform.service.ApiTagService;
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
@Api(tags = "标签")
@RestController
@RequestMapping("/api/tag")
public class ApiTagController extends ApiBaseAction {

    @Autowired
    private ApiTagService tagService;

    private final static Integer point = 5;

    @RequestMapping("/list")
    public R list(Long oId) {
        if(oId == null){
            List<TagVo> tags = tagService.queryFirstLevelTags();
            return R.ok().put("msg", tags);
        } else {

            List<TagVo> tags = tagService.queryByFirstLevelTags(oId);
            return R.ok().put("msg", tags);
        }
    }

}