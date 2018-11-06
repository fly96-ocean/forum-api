package com.platform.api;

import com.platform.entity.DomainVo;
import com.platform.service.ApiDomainService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.Constant;
import com.platform.utils.R;
import com.platform.validator.Assert;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "帖子分类")
@RestController
@RequestMapping("/api/domain")
public class ApiDomainController extends ApiBaseAction {
    @Autowired
    private ApiDomainService domainService;

    @RequestMapping("/list")
    public R list(Long domainType) {
        Assert.isNull(domainType, "帖子分类不能为空");
        Map map = new HashMap();
        map.put("domainType", domainType);
        List<DomainVo> domainVos = domainService.queryList(map);

        return R.ok().put("msg", domainVos);
    }
}