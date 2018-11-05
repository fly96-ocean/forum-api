package com.platform.service;

import com.platform.dao.ApiFollowMapper;
import com.platform.dao.ApiTagMapper;
import com.platform.dao.ApiTagTagMapper;
import com.platform.entity.FollowVo;
import com.platform.entity.TagTagVo;
import com.platform.entity.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ApiTagService {

    @Autowired
    private ApiTagMapper tagDao;
    @Autowired
    private ApiTagTagMapper tagTagDao;

    public TagVo queryObject(Integer id) {
        return tagDao.queryObject(id);
    }


    public List<TagVo> queryList() {
        return tagDao.queryList();
    }


    public int queryTotal(Map<String, Object> map) {
        return tagDao.queryTotal(map);
    }


    public void save(TagVo tagVo) {
        tagDao.save(tagVo);
    }


    public void update(TagVo tagVo) {
        tagDao.update(tagVo);
    }

    public List<TagVo> queryFirstLevelTags(){
        List<TagTagVo> tagTagVos = tagTagDao.queryOId1List();
        List<Long> ids = new ArrayList<>();
        for(TagTagVo tagTagVo:tagTagVos){
            ids.add(tagTagVo.getTag1Oid());
        }
        return tagDao.queryByIds(ids);
    }

    public List<TagVo> queryByFirstLevelTags(Long oId){
        List<TagTagVo> tagTagVos = tagTagDao.queryOId2List(oId);
        List<Long> ids = new ArrayList<>();
        for(TagTagVo tagTagVo:tagTagVos){
            ids.add(tagTagVo.getTag1Oid());
        }
        return tagDao.queryByIds(ids);
    }
}
