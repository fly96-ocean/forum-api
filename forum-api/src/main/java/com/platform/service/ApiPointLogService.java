package com.platform.service;

import com.platform.dao.ApiPointLogMapper;
import com.platform.entity.PointLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ApiPointLogService {

    @Autowired
    private ApiPointLogMapper pointLogDao;

    public PointLogVo queryObject(Integer id) {
        return pointLogDao.queryObject(id);
    }


    public List<PointLogVo> queryList() {
        return pointLogDao.queryList();
    }


    public List<PointLogVo> queryList(Map<String, Object> map) {
        return pointLogDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return pointLogDao.queryTotal(map);
    }


    public void save(PointLogVo pointLogVo) {
        pointLogDao.save(pointLogVo);
    }


    public void update(PointLogVo pointLogVo) {
        pointLogDao.update(pointLogVo);
    }

    public int querySum(Map<String, Object> map)  {
        Integer sum = pointLogDao.querySum(map);
        if(sum == null){
            return 0;
        }else{
            return sum;
        }
    }

    public List<Map<Date,Integer>> pointTotal(Map<String, Object> map){
        return pointLogDao.pointTotal(map);
    }

}
