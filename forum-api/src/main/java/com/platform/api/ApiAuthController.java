package com.platform.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
//import com.platform.service.ApiUserService;
import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.service.TokenService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiUserUtils;
import com.platform.util.CommonUtil;
import com.platform.utils.CharUtil;
import com.platform.utils.R;
import com.platform.validator.Assert;
import com.qiniu.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API登录授权
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ApiUserService userService;

    /**
     *  获取token
     */
    @IgnoreAuth
    @RequestMapping("token")
    @ApiOperation(value = "获取账号")
    public R token(Long userId) {

        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }

    /**
     *  获取token
     */
    @IgnoreAuth
    @RequestMapping("loginByPassword")
    @ApiOperation(value = "登录接口")
    public R loginByPassword(String userName, String userPassword) {
        Assert.isBlank(userName, "账户不能为空");
        Assert.isBlank(userPassword, "密码不能为空");

        userPassword = new Sha256Hash(userPassword).toHex();

        UserVo userVo = userService.login(userName, userPassword);
        return R.ok().put("msg", userVo);
    }

    @IgnoreAuth
    @RequestMapping("login")
    @ApiOperation(value = "登录接口")
    public R login(String userId) {
        Assert.isBlank(userId, "用户ID不能为空");

        JSONObject userInfo = this.getUserInfo(userId).getJSONObject("userInfo");

        String loginIP = this.getClientIp();
        if(userInfo != null){
            UserVo userVo = userService.login(userId,userInfo,loginIP);
            return R.ok().put("msg", userVo);
        }else{
            return R.error().put("msg", "未知异常，请联系管理员");
        }

    }
}
