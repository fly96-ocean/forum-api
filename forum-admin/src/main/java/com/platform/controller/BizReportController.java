package com.platform.controller;

import com.platform.entity.*;
import com.platform.service.ApiArticleService;
import com.platform.service.ApiUserService;
import com.platform.service.BizArticleService;
import com.platform.service.BizReportService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.ShiroUtils;
import com.platform.validator.Assert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("report")
public class BizReportController {
    @Autowired
    private BizReportService reportService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("article:list")
    public R list(@RequestParam Map<String, Object> params) {
        Object reportType = params.get("reportType");
        Assert.isNull(reportType, "举报类型不能为空");

        //查询列表数据
        Query query = new Query(params);

        List<BizReportEntity> articles = reportService.queryList(query);
        int total = reportService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(articles, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        Object reportType = params.get("reportType");
        Assert.isNull(reportType, "举报类型不能为空");

        List<BizReportEntity> list = reportService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("report:info")
    public R info(@PathVariable("id") Long id) {
        BizReportEntity report = reportService.queryObject(id);

        return R.ok().put("report", report);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("report:update")
    public R update(@RequestBody BizReportEntity report) {
        reportService.update(report);
        return R.ok();
    }

    /**
     * 设置处理封贴
     */
    @RequestMapping("/handle")
    @RequiresPermissions("report:handle")
    public R setPerfect(@RequestBody Long[] ids) {

        return R.ok();
    }
    /**
     * 设置精华帖
     */
    @RequestMapping("/ignore")
    @RequiresPermissions("article:handle")
    public R cancelPerfect(@RequestBody Long[] ids) {
        return R.ok();
    }

}
