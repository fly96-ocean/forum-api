package com.platform.controller;

import com.platform.entity.BizForumUserEntity;
import com.platform.entity.BizReportEntity;
import com.platform.service.BizForumUserService;
import com.platform.service.BizReportService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.validator.Assert;
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
@RequestMapping("forumUser")
public class BizForumUserController {
    @Autowired
    private BizForumUserService forumUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("forumUser:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<BizForumUserEntity> reports = forumUserService.queryList(query);
        int total = forumUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(reports, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<BizForumUserEntity> list = forumUserService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("forumUser:info")
    public R info(@PathVariable("id") Long id) {
        BizForumUserEntity forumUserEntity = forumUserService.queryObject(id);

        return R.ok().put("report", forumUserEntity);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("forumUser:update")
    public R update(@RequestBody BizForumUserEntity forumUserEntity) {
        forumUserService.update(forumUserEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("forumUser:delete")
    public R forbid(@RequestBody Long[] ids) {
        forumUserService.deleteOrNot(ids, 1);
        return R.ok();
    }

    @RequestMapping("/active")
    @RequiresPermissions("forumUser:active")
    public R unForbid(@RequestBody Long[] ids) {
        forumUserService.deleteOrNot(ids, 0);
        return R.ok();
    }
}
