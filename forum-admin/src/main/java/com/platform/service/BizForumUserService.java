package com.platform.service;

import com.platform.entity.BizForumUserEntity;
import com.platform.entity.BizReportEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传Service
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-25 12:13:26
 */
public interface BizForumUserService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    BizForumUserEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<BizForumUserEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param forumUserEntity 实体
     * @return 保存条数
     */
    void save(BizForumUserEntity forumUserEntity);

    /**
     * 根据主键更新实体
     *
     * @param forumUserEntity 实体
     * @return 更新条数
     */
    void update(BizForumUserEntity forumUserEntity);

    /**
     * 删除或激活用户
     * @param ids
     * @param status
     */
    void deleteOrNot(Long[] ids, Integer status);


}
