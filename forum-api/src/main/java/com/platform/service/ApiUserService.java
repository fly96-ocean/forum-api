package com.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.platform.dao.ApiFollowMapper;
import com.platform.dao.ApiUserMapper;
import com.platform.entity.FollowVo;
import com.platform.entity.UserVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public List<UserVo> queryListUserFollow(Long userId){
        return userDao.queryListUserFollow(userId);
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

    @Transactional
    public UserVo login(String userId, JSONObject userInfo, String loginIP){
        UserVo userVo = userDao.queryByUserLoginId(userId);
        Date currentDate = new Date();

        if(userVo!=null){
            return userVo;
//            userVo.setUserName(userInfo.getString("username"));
//            userVo.setUserNickname(userInfo.getString("name"));
//            userVo.setUserLoginId(userInfo.getString("userId"));
//            userVo.setUserPoint(userInfo.getInteger("score"));
//            userVo.setUserShopId(userInfo.getString("shop_id"));
//            userVo.setUserShopName(userInfo.getString("shop_name"));
//            userVo.setUserDistributorId(userInfo.getString("distributor_id"));
//            userVo.setUserDistributorId(userInfo.getString("distributor_name"));
//            userVo.setUserUpdateTime(currentDate);
//            userVo.setUserLatestLoginTime(currentDate);
//            userVo.setUserLatestLoginIP(loginIP);
//
//            userDao.update(userVo);

        } else {
            UserVo userVo1 = new UserVo();
            userVo1.setUserName(userInfo.getString("username"));
            userVo1.setUserNickname(userInfo.getString("name"));
            userVo1.setUserEmail("123@qq.com");
            userVo1.setUserAvatarType(1);
            userVo1.setUserAvatarURL("/afafd/afafa");
            userVo1.setUserLoginId(userInfo.getString("user_id"));
            userVo1.setUserPoint(0);
            userVo1.setUserPlatformScore(userInfo.getInteger("score"));
            userVo1.setUserShopId(userInfo.getString("shop_id"));
            userVo1.setUserShopName(userInfo.getString("shop_name"));
            userVo1.setUserDistributorId(userInfo.getString("distributor_id"));
            userVo1.setUserDistributorName(userInfo.getString("distributor_name"));
            userVo1.setUserUpdateTime(currentDate);
            userVo1.setUserLatestLoginTime(currentDate);
            userVo1.setUserLatestLoginIP(loginIP);
            userVo1.setLevel(0);
            userVo1.setUserOnlineFlag(0);
            userVo1.setUserRole("defaultRole");

            userDao.save(userVo1);

//            UserVo userVo2 = userDao.queryObject(userVo1.getoId());

            return userVo1;
        }

    }
}
