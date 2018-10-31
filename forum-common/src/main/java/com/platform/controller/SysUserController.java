package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.entity.SysDeptEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysDeptService;
import com.platform.service.SysUserRoleService;
import com.platform.service.SysUserService;
import com.platform.utils.*;
import com.platform.utils.excel.ExcelImport;
import com.platform.validator.Assert;
import com.platform.validator.ValidatorUtils;
import com.platform.validator.group.AddGroup;
import com.platform.validator.group.UpdateGroup;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }

        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        if(ResourceUtil.getConfigByName("sys.demo").equals("1")){
            throw new RRException("演示环境无法修改密码！");
        }
        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }

        //退出
        ShiroUtils.logout();

        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.save(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.update(user);

        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return R.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return R.ok();
    }

    @SysLog("导入解析部门信息")
    @RequestMapping("/importUser")
    @RequiresPermissions("sys:dept:importDept")
    public R importDept(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        List<SysUserEntity> sysUserEntities = new ArrayList<>();
        List<String[]> datas = ExcelImport.getExcelData(file);
        for(String[] data : datas){
            String employeeCode = data[0];
            String userName = data[1];
            String account = data[2];
            String topDept = data[3];
            String secDept = data[5];
            Map<String, Object> params = new HashMap();
            params.put("name", topDept);
            System.out.println(params);
            List<SysDeptEntity> deptEntities = sysDeptService.queryList(params);
            Long parentId = deptEntities.get(0).getDeptId();

            Map<String, Object> parmasTwo = new HashMap<>();
            parmasTwo.put("parentId", parentId);
            parmasTwo.put("name", secDept);

            List<SysDeptEntity> deptEntitiesTwo = sysDeptService.queryList(parmasTwo);
            Long deptId = deptEntitiesTwo.get(0).getDeptId();


            String email = data[6];

            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setEmployeeCode(employeeCode);
            sysUserEntity.setUsername(userName);
            sysUserEntity.setAccount(account);
            sysUserEntity.setDeptId(deptId);
            sysUserEntity.setDeptName(secDept);
            sysUserEntity.setEmail(email);
            sysUserEntity.setPoints(0);
            sysUserEntity.setCreateUserId(1L);
            sysUserEntity.setStatus(1);
            sysUserEntities.add(sysUserEntity);

        }

        sysUserService.saveBatch(sysUserEntities);

        R r = new R();
        return r;
    }
}
