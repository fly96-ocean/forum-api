package com.platform.service;

import com.platform.dao.ApiFollowMapper;
import com.platform.dao.ApiTagMapper;
import com.platform.dao.ApiTagTagMapper;
import com.platform.entity.DomainVo;
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

    public TagVo queryObject(Long id) {
        return tagDao.queryObject(id);
    }


    public List<TagVo> queryList(Map<String, Object> map) {
        return tagDao.queryList(map);
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

    public List<TagVo> queryBySecondLevelTags(Long oId){
        List<TagTagVo> tagTagVos = tagTagDao.queryOId2List(oId);
        List<Long> ids = new ArrayList<>();
        for(TagTagVo tagTagVo:tagTagVos){
            ids.add(tagTagVo.getTag2Oid());
        }
        return tagDao.queryByIds(ids);
    }

    public int serverQueryTotal(Map<String, Object> map){
        return tagDao.serverQueryTotal(map);
    }

    public List<TagVo> serverQueryList(Map<String, Object> map){
        return tagDao.serverQueryList(map);
    }

    public void deleteOrNot(Long[] ids, Integer status) {
        for(int i = 0; i<ids.length; i++){
            if(ids[i]!=null){
                TagVo tag = tagDao.queryObject(ids[i]);
                tag.setTagStatus(status);
                tagDao.update(tag);
            }

        }
    }
}
