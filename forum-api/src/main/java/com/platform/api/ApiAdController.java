package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.AdVo;
import com.platform.entity.DomainVo;
import com.platform.service.ApiAdService;
import com.platform.service.ApiDomainService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import com.platform.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "顶部广告")
@RestController
@RequestMapping("/api/ad")
public class ApiAdController extends ApiBaseAction {

    @Autowired
    private ApiAdService adService;

    @RequestMapping("/list")
    public R list() {
        List<AdVo> adVoList = adService.queryList();

        return R.ok().put("msg", adVoList);
    }

}