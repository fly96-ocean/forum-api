package com.platform.dao;

import com.platform.entity.AdVo;

import java.util.List;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiAdMapper extends BaseDao<AdVo> {
    List<AdVo> queryIncome(Long userId);
    List<AdVo> queryOutcome(Long userId);

    //获取该部门下的登陆次数
    Long countDeptLoginPoint(Long deptId);

    //获取该部门下当月的登陆次数
    Long countCurrentMonthDeptLoginPoint(Long deptId);

    Long countUserTodayLoginPoint(Long userId);
}
