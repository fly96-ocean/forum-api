package com.platform.service;

import com.platform.dao.ApiFollowMapper;
import com.platform.dao.ApiUserMapper;
import com.platform.entity.FollowVo;
import com.platform.entity.UserVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class ApiUserService {
    @Autowired
    private ApiUserMapper userDao;
    @Autowired
    private ApiFollowMapper followDao;

    public UserVo queryObject(Long userId) {
        return userDao.queryObject(userId);
    }

    public List<UserVo> queryList(Map<String, Object> map) {
        return userDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return userDao.queryTotal(map);
    }

    public void update(UserVo userVo) {
        userDao.update(userVo);
    }

    public List<UserVo> queryListByUserFriends(Long userId) {
        return userDao.queryListByUserFriends(userId);
    }

    public List<UserVo> queryListByUserFans(Long userId) {
        return userDao.queryListByUserFans(userId);
    }

    @Transactional
    public void follow(FollowVo followVo){

        UserVo userVo = userDao.queryObject(followVo.getFollowingId());
        Integer userFansCount = userVo.getUserFansCount() + 1;
        userVo.setUserFansCount(userFansCount);

        followDao.save(followVo);
        userDao.update(userVo);
    }

    @Transactional
    public void cancelFollow(Long followerId, Long followingId){

        UserVo userVo = userDao.queryObject(followingId);
        Integer userFansCount = userVo.getUserFansCount() - 1;
        userVo.setUserFansCount(userFansCount);

        followDao.deleteByFollowerIdAndFollowingIdAndFollowingType(followerId,followingId,0);
        userDao.update(userVo);
    }

    public UserVo login(String userName, String userPassword){
        List<UserVo> users = userDao.queryByUserNameAndPass(userName, userPassword);
        if(users!=null && users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }
}
