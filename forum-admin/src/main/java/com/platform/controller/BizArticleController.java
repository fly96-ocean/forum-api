package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.entity.ArticleVo;
import com.platform.entity.PointLogVo;
import com.platform.entity.SysUserEntity;
import com.platform.entity.UserVo;
import com.platform.service.ApiArticleService;
import com.platform.service.ApiUserService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.ShiroUtils;
import com.platform.validator.Assert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 09:37:35
 */
@RestController
@RequestMapping("article")
public class BizArticleController extends ApiBaseAction {
    @Autowired
    private ApiArticleService articleService;
    @Autowired
    private ApiUserService userService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("article:list")
    public R list(@RequestParam Map<String, Object> params) {
        Object reportType = params.get("articleType");
        Assert.isNull(reportType, "类型不能为空");
        //查询列表数据
        Query query = new Query(params);

        List<ArticleVo> articles = articleService.serverQueryList(query);
        int total = articleService.serverQueryTotal(query);

        PageUtils pageUtil = new PageUtils(articles, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ArticleVo> list = articleService.serverQueryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("article:info")
    public R info(@PathVariable("id") Long id) {
        ArticleVo articleVo = articleService.queryObject(id);

        return R.ok().put("article", articleVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("article:save")
    public R save(@RequestBody ArticleVo articleVo) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        UserVo userVo = userService.queryByUserNo(sysUserEntity.getEmployeeCode());

        PointLogVo pointLogVo = new PointLogVo();
        pointLogVo.setPointLogArticleAuthorId(userVo.getoId());
        pointLogVo.setPointLogType(0);
        pointLogVo.setPointLogCreateTime(new Date());
        pointLogVo.setPointLogPoint(0);

        articleVo.setArticleAuthorId(userVo.getoId());
        articleVo.setArticleCreateTime(new Date());
        articleVo.setArticleQnAOfferPoint(0);

        articleService.saveAndUpdate(articleVo, userVo, pointLogVo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("article:update")
    public R update(@RequestBody ArticleVo articleVo) {
//        ArticleVo article = articleService.queryObject(articleVo.getoId());
        articleVo.setArticleUpdateTime(new Date());
        articleService.update(articleVo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("article:delete")
    public R forbid(@RequestBody Long[] ids) {
        articleService.deleteOrNot(ids, 1);
        return R.ok();
    }

    @RequestMapping("/active")
    @RequiresPermissions("article:active")
    public R unForbid(@RequestBody Long[] ids) {
        articleService.deleteOrNot(ids, 0);
        return R.ok();
    }

    /**
     * 设置精华帖
     */
    @RequestMapping("/setPerfect")
    @RequiresPermissions("article:update")
    public R setPerfect(@RequestParam Map<String, Object> params) {
        Assert.isNull(params.get("ids"), "ID不能为空");
        String[] idsStr = ((String) params.get("ids")).split(",");
        Long[] ids = new Long[idsStr.length];
        for(int i = 0; i<idsStr.length; i++){
            ids[i] = Long.parseLong(idsStr[i]);
        }
        String pointStr = (String)params.get("point");
        Integer point = Integer.parseInt(pointStr);
        articleService.setPerfectOrNot(ids, 1, point);

        for(int i = 0; i<ids.length; i++) {
            if (ids[i] != null) {
                ArticleVo articleVo = articleService.queryObject(ids[i]);
                this.updateUserScore(articleVo.getArticleAuthorId()+"", point, "管理员设置或取消精华帖");
            }
        }
        return R.ok();
    }
    /**
     * 设置精华帖
     */
    @RequestMapping("/cancelPerfect")
    @RequiresPermissions("article:update")
    public R cancelPerfect(@RequestParam Map<String, Object> params) {
        Assert.isNull(params.get("ids"), "ID不能为空");
        String[] idsStr = ((String) params.get("ids")).split(",");
        Long[] ids = new Long[idsStr.length];
        for(int i = 0; i<idsStr.length; i++){
            ids[i] = Long.parseLong(idsStr[i]);
        }
        String pointStr = (String)params.get("point");
        Integer point = Integer.parseInt(pointStr);
        if(point>0){
            point = -point;
        }

        articleService.setPerfectOrNot(ids, 0, point);


        return R.ok();
    }
    /**
     * 设置置顶
     */
    @RequestMapping("/stick")
    @RequiresPermissions("article:update")
    public R stick(@RequestBody Long[] ids) {
        articleService.stickOrNot(ids, 1);
        return R.ok();
    }

    /**
     * 设置置顶
     */
    @RequestMapping("/cancelStick")
    @RequiresPermissions("article:update")
    public R cancelStick(@RequestBody Long[] ids) {
        articleService.stickOrNot(ids, 0);
        return R.ok();
    }

}
