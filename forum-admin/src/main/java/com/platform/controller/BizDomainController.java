package com.platform.controller;

import com.platform.entity.ArticleVo;
import com.platform.entity.DomainVo;
import com.platform.service.ApiArticleService;
import com.platform.service.ApiDomainService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 09:37:35
 */
@RestController
@RequestMapping("domain")
public class BizDomainController {
    @Autowired
    private ApiDomainService domainService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("domain:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<DomainVo> domains = domainService.serverQueryList(query);
        int total = domainService.serverQueryTotal(query);

        PageUtils pageUtil = new PageUtils(domains, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<DomainVo> list = domainService.serverQueryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("domain:info")
    public R info(@PathVariable("id") Long id) {
        DomainVo domain = domainService.queryObject(id);

        return R.ok().put("domain", domain);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("domain:save")
    public R save(@RequestBody DomainVo domain) {
        domainService.save(domain);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("domain:update")
    public R update(@RequestBody DomainVo domain) {
        domainService.update(domain);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("domain:update")
    public R delete(@RequestBody Long[] ids) {
        domainService.deleteOrNot(ids, 1);
        return R.ok();
    }

    @RequestMapping("/active")
    @RequiresPermissions("domain:update")
    public R active(@RequestBody Long[] ids) {
        domainService.deleteOrNot(ids, 0);
        return R.ok();
    }

}
