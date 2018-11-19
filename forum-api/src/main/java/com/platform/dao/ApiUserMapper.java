package com.platform.dao;

import com.platform.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiUserMapper extends BaseDao<UserVo> {

    public List<UserVo> queryListByUserFriends(@Param("userId") Long userId);
    public List<UserVo> queryListByUserFans(@Param("userId") Long userId);
    public List<UserVo> queryByUserNameAndPass(@Param("userName") String userName, @Param("userPassword") String userPassword);
    public UserVo queryByUserLoginId(@Param("userLoginId") String userLoginId);
}
