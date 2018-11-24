package com.platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.entity.TokenEntity;
import com.platform.interceptor.AuthorizationInterceptor;
import com.platform.service.TokenService;
import com.platform.validator.Assert;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author harmon
 * @ClassName: ApiBaseAction
 * @Description: 基础控制类
 * @date 2016年9月2日
 */
public class ApiBaseAction {
    protected Logger logger = Logger.getLogger(getClass());
    /**
     * 得到request对象
     */
    @Autowired
    protected HttpServletRequest request;
    /**
     * 得到response对象
     */
    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected TokenService tokenService;

    private static final String userInfoURL = "http://manage.dadel.cn/MtServer/external/getUserInfo.do";
    private static final String userScoreURL = "http://manage.dadel.cn/MtServer/external/updateUserScore.do";
    private static final String privatekey = "123456";
    private static final String account = "DC001";
    /**
     * 参数绑定异常
     */
    @ExceptionHandler({BindException.class, MissingServletRequestParameterException.class, UnauthorizedException.class, TypeMismatchException.class})
    @ResponseBody
    public Map<String, Object> bindException(Exception e) {
        if (e instanceof BindException) {
            return toResponsObject(1, "参数绑定异常", e.getMessage());
        } else if (e instanceof UnauthorizedException) {
            return toResponsObject(1, "无访问权限", e.getMessage());
        }
        return toResponsObject(1, "处理异常", e.getMessage());
    }

    /**
     * @param requestCode
     * @param msg
     * @param data
     * @return Map<String,Object>
     * @throws
     * @Description:构建统一格式返回对象
     * @date 2016年9月2日
     * @author zhuliyun
     */
    public Map<String, Object> toResponsObject(int requestCode, String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", requestCode);
        obj.put("errmsg", msg);
        if (data != null)
            obj.put("data", data);
        return obj;
    }

    public Map<String, Object> toResponsSuccess(Object data) {
        Map<String, Object> rp = toResponsObject(0, "执行成功", data);
        logger.info("response:" + rp);
        return rp;
    }

    public Map<String, Object> toResponsMsgSuccess(String msg) {
        return toResponsObject(0, msg, "");
    }

    public Map<String, Object> toResponsSuccessForSelect(Object data) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("list", data);
        return toResponsObject(0, "执行成功", result);
    }

    public Map<String, Object> toResponsFail(String msg) {
        return toResponsObject(1, msg, null);
    }

    /**
     * initBinder 初始化绑定 <br>
     * 这里处理了3种类型<br>
     * 1、字符串自动 trim 去掉前后空格 <br>
     * 2、java.util.Date 转换为 "yyyy-MM-dd HH:mm:ss" 格式<br>
     * 3、java.sql.Date 转换为 "yyyy-MM-dd" 格式<br>
     * 4、java.util.Timestamps 时间转换
     *
     * @param binder  WebDataBinder 要注册的binder
     * @param request 前端请求
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        // 字符串自动Trim
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    /**
     * 获取请求方IP
     *
     * @return 客户端Ip
     */
    public String getClientIp() {
        String xff = request.getHeader("x-forwarded-for");
        if (xff == null) {
            return "8.8.8.8";
        }
        return xff;
    }

    /**
     * 获取请求方浏览器情况
     *
     * @return 客户端浏览器信息
     */
    public String getClientUA() {
        String userAgent = request.getHeader("User-Agent");
        String user = userAgent.toLowerCase();
        String browser = new String();
        if (user.contains("edge")) {
            browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("msie")) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
                    + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
                        + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                        .replace("OPR", "Opera");
            }

        } else if (user.contains("chrome")) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) ||
                (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) ||
                (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
            browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
        } else {
            browser = "UnKnown, More-Info: " + userAgent;

        }
        return browser;
    }

    public JSONObject getJsonRequest() {
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader();) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
            result = JSONObject.parseObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取请求的用户Id
     *
     * @return 客户端Ip
     */
    public Long getUserId() {
        String token = request.getHeader(AuthorizationInterceptor.LOGIN_USER_KEY);
        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            return null;
        }
        return tokenEntity.getUserId();
    }

    /*
    * 获得用户信息
    *
    * */
    public JSONObject getUserInfo(String userId) {
        Assert.isNull(userId, "用户Id不能为空");
        JSONObject result = null;

        String nonce = UUID.randomUUID().toString();

        String signature = md5(nonce + privatekey);

        String param = "{\"nonce\":\""+ nonce +"\",\"privatekey\":\"" + privatekey
                + "\",\"account\":\""+ account + "\",\"signature\":\""+ signature
                +"\",\"user_id\":\"" + userId + "\"}";

        String userInfo = sendPost(userInfoURL, param);

        result = JSON.parseObject(userInfo);

        return  result;
    }


    // 更新用户积分
    public JSONObject updateUserScore(String userId, Integer score, String remark) {
        Assert.isNull(score, "积分不能为空");

        JSONObject result = null;

        String nonce = UUID.randomUUID().toString();

        String signature = md5(nonce + privatekey);

        String param = "{\"nonce\":\""+ nonce +"\",\"privatekey\":\"" + privatekey
                + "\",\"account\":\""+ account + "\",\"signature\":\""+ signature
                +"\",\"user_id\":\"" + userId + "\",\"score\":"+ score + ",\"remark\":\"" + remark +"\"}" ;

        String userScore = sendPost(userScoreURL, param);

        result = JSON.parseObject(userScore);

        return  result;
    }

    // sendPost Method
    public static String sendPost(String url, String parameters) {
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            java.net.URL connURL = new java.net.URL(url);
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Content-Length", String.valueOf(parameters.getBytes("UTF-8").length));
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            out = new PrintWriter(httpConn.getOutputStream());
            out.write(parameters);
            out.flush();
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // ????????????
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }



}
