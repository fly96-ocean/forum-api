package com.platform.controller;

import com.platform.entity.DomainVo;
import com.platform.entity.TagVo;
import com.platform.service.ApiDomainService;
import com.platform.service.ApiTagService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
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
@RequestMapping("tag")
public class BizTagController {
    @Autowired
    private ApiTagService tagService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tag:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TagVo> tags = tagService.serverQueryList(query);
        int total = tagService.serverQueryTotal(query);

        PageUtils pageUtil = new PageUtils(tags, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TagVo> list = tagService.serverQueryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tag:info")
    public R info(@PathVariable("id") Long id) {
        TagVo tag = tagService.queryObject(id);

        return R.ok().put("tag", tag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tag:save")
    public R save(@RequestBody TagVo tag) {
        tagService.save(tag);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tag:update")
    public R update(@RequestBody TagVo tag) {
        tagService.update(tag);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tag:update")
    public R delete(@RequestBody Long[] ids) {
        tagService.deleteOrNot(ids, 1);
        return R.ok();
    }

    @RequestMapping("/active")
    @RequiresPermissions("tag:update")
    public R active(@RequestBody Long[] ids) {
        tagService.deleteOrNot(ids, 0);
        return R.ok();
    }


}
