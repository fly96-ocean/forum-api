package com.platform.service;

import com.platform.entity.BizReportEntity;
import com.platform.entity.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传Service
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-25 12:13:26
 */
public interface BizReportService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    BizReportEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<BizReportEntity> queryList(Map<String, Object> map);

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
     * @param reportEntity 实体
     * @return 保存条数
     */
    void save(BizReportEntity reportEntity);

    /**
     * 根据主键更新实体
     *
     * @param reportEntity 实体
     * @return 更新条数
     */
    void update(BizReportEntity reportEntity);

}
