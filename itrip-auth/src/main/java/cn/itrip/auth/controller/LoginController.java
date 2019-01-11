package cn.itrip.auth.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itrip.common.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itrip.auth.exception.UserLoginFailedException;
import cn.itrip.auth.service.TokenService;
import cn.itrip.auth.service.UserService;
import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.ItripTokenVO;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.HttpStatus;
import ytx.org.apache.http.client.methods.HttpGet;
import ytx.org.apache.http.impl.client.DefaultHttpClient;
import ytx.org.apache.http.util.EntityUtils;

/**
 * 用户登录控制器
 *
 * @author hduser
 */
@Controller
@RequestMapping(value = "/api")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Resource
    private ValidationToken validationToken;

    private static final String getToken = "token";

    @RequestMapping(value = "/dologin", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Dto dologin(@RequestParam String name, @RequestParam String password, HttpServletRequest request) {
        if (!EmptyUtils.isEmpty(name) && !EmptyUtils.isEmpty(password)) {
            ItripUser user = null;
            try {
                System.out.println("MD5.getMd5(password.trim()>>>" + MD5.getMd5(password.trim(), 32));
                user = userService.login(name.trim(), MD5.getMd5(password.trim(), 32));
            } catch (UserLoginFailedException e) {
                return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_AUTHENTICATION_FAILED);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
            }
            if (EmptyUtils.isNotEmpty(user)) {
                String token = tokenService.generateToken(
                        request.getHeader("user-agent"), user);
                System.out.println("token>>>>>>================" + token);
                tokenService.save(token, user);

                //返回ItripTokenVO

                long expTime = Calendar.getInstance().getTimeInMillis() + TokenService.SESSION_TIMEOUT * 1000;
                long genTime = Calendar.getInstance().getTimeInMillis();
                System.out.println("expTime>>>" + expTime + " " + "genTime>>>" + genTime);
                ItripTokenVO tokenVO = new ItripTokenVO(token, expTime, genTime);
                System.out.println("tokenVO>>>>" + tokenVO.getToken());
                return DtoUtil.returnDataSuccess(tokenVO);
            } else {
                return DtoUtil.returnFail("用户名密码错误", ErrorCode.AUTH_AUTHENTICATION_FAILED);
            }
        } else {
            return DtoUtil.returnFail("参数错误！检查提交的参数名称是否正确。", ErrorCode.AUTH_PARAMETER_ERROR);
        }
    }

    @ApiOperation(value = "用户注销", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "客户端需在header中发送token")
    @ApiImplicitParam(paramType = "header", required = true, name = "token", value = "用户认证凭据", defaultValue = "PC-yao.liu2015@bdqn.cn-8-20170516141821-d4f514")
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json", headers = "token")
    public @ResponseBody
    Dto logout(HttpServletRequest request) {
        //验证token
        String token = request.getHeader("token");
        if (!tokenService.validate(request.getHeader("user-agent"), token))
            return DtoUtil.returnFail("token无效", ErrorCode.AUTH_TOKEN_INVALID);
        //删除token和信息
        try {
            tokenService.delete(token);
            return DtoUtil.returnSuccess("注销成功");
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("注销失败", ErrorCode.AUTH_UNKNOWN);
        }
    }

    /**
     * token置换
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/retoken", method = RequestMethod.GET, produces = "application/json", headers = "token")
    public @ResponseBody
    Dto reloadToken(HttpServletRequest request) {
        String token;
        System.out.println("retoken>>>>>>>>>>>>>>>>>>>>>>");
        try {
            token = this.tokenService.replaceToken(request.getHeader("user-agent"), request.getHeader("token"));
            ItripTokenVO vo = new ItripTokenVO(token, Calendar.getInstance().getTimeInMillis() * 2 * 60 * 60 * 1000, Calendar.getInstance().getTimeInMillis());
            return DtoUtil.returnDataSuccess(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getLocalizedMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }

    /**
     * token验证
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/validateToken", method = RequestMethod.GET, produces = "application/json", headers = "token")
    public @ResponseBody
    Dto validateToken(HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        System.out.println("tokenString>>>>>>" + tokenString);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        if (null != currentUser) {
            System.out.println("getId" + currentUser.getId());
            System.out.println("getUserName" + currentUser.getUserName());
            System.out.println("getUserCode" + currentUser.getUserCode());
            System.out.println("getUserPassword" + currentUser.getUserPassword());
            return DtoUtil.returnSuccess("获取登录用户信息成功", currentUser);
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }

    /**
     * ajax获取用户列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUserList", produces = "application/json", method = RequestMethod.GET, headers = "token")
    @ResponseBody
    public Dto getUserList(HttpServletRequest request) {
        System.out.println("getUserList>>>>>>>>>>>>>>>>>>");
        String tokenString = request.getHeader("token");
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUser> list = null;
        try {
            if (EmptyUtils.isEmpty(currentUser)) {
                return DtoUtil.returnFail("token失效，请重登录", "100000");
            } else {
                list = userService.findAll();
                return DtoUtil.returnSuccess("获取成功", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }

    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("=======进入login=========>>");
        String url = "https://open.weixin.qq.com/connect/qrconnect?";
        url += "appid=wx3e9342f14cd7e34f";
        url += "&redirect_uri=" + URLEncoder.encode("http://490707ae.ngrok.io/itrip-auth/api/callBackLogin", "GBK");
        url += "&response_type=code&scope=snsapi_login";
        url += "&state=" + request.getSession().getId() + "#wechat_redirect";
        System.out.println("url>>>" + url);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/callBackLogin")
    public String callBackLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("callBackLogin....");
        // return "redirect:../loginSuccess.jsp";
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println("code=" + code);
        System.out.println("state=" + state);
        // 获得access_token数据，获得访问令牌。等下要通过令牌去获得用户的信息
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
        url += "appid=wx3e9342f14cd7e34f";
        url += "&secret=f30f8137a7a90d9e4c80530c920664e1";
        url += "&code=" + code + "&grant_type=authorization_code";
        // 要去执行这个URL，并通过这个URL获得返回值
        JSONObject jsonObject = this.httpGet(url);
        String at = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");
        System.out.println("at=================" + at);
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + at + "&openid=" + openId;
        jsonObject = this.httpGet(url, request);
        System.out.println("=======================================jsonObject" + jsonObject);
        System.out.println("=======================================jsonObject" + jsonObject.getString(getToken));
        /*response.setHeader(getToken,jsonObject.getString(getToken));
        request.getHeader("user-agent");
        request.getHeader("Cookie");*/
        Cookie cookie = new Cookie(getToken, jsonObject.getString(getToken));
        response.addCookie(cookie);
        /*return "redirect:/getUserList";*/
        return "getUserList";
        /* return "redirect: /api/getUserList.html";*/
    }

    /**
     * 发送get请求 http://www.cnblogs.com/QQParadise/articles/5020215.html
     *
     * @param url 路径
     * @return
     */
    public JSONObject httpGet(String url) throws Exception {
        // get请求返回结果
        JSONObject jsonResult = null;
        ItripUser itripUser;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                String strResult = EntityUtils.toString(response.getEntity());
                System.out.println("strResult..." + strResult);
                String str = new String(strResult.getBytes("ISO-8859-1"), "UTF-8");
                System.out.println("str..." + str);
                /** 把json字符串转换成json对象 **/
                jsonResult = JSON.parseObject(str);
                String openid = jsonResult.getString("openid");
                System.out.println("openid==========11111111111========" + openid);
                System.out.println("strResult=" + str);
            } else {
                System.out.println("读取数据失败..");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public JSONObject httpGet(String url, HttpServletRequest httpServletRequestrequest) throws Exception {
        // get请求返回结果
        JSONObject jsonResult = null;
        JSONObject jsonResult1 = null;
        ItripUser itripUser;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                String strResult = EntityUtils.toString(response.getEntity());
                System.out.println("strResult..." + strResult);
                String str = new String(strResult.getBytes("ISO-8859-1"), "UTF-8");
                System.out.println("str..." + str);
                /** 把json字符串转换成json对象 **/
                jsonResult = JSON.parseObject(str);
                String openid = jsonResult.getString("openid");
                String nickname = jsonResult.getString("nickname");
                System.out.println("openid==================" + openid);
                if (null == userService.findByUsername(openid)) {
                    itripUser = new ItripUser();
                    itripUser.setUserCode(openid);
                    itripUser.setUserName("微信");
                    itripUser.setUserPassword("e10adc3949ba59abbe56e057f20f883e");
                    itripUser.setActivated(1);//激活用户
                    itripUser.setUserType(1);//微信用户
                    userService.itriptxCreateUser(itripUser);
                }
                if (null != userService.findByUsername(openid)) {
                    itripUser = userService.findByUsername(openid);
                    Dto<ItripTokenVO> dto = this.dologin(itripUser.getUserCode(), "123456", httpServletRequestrequest);
                    ItripTokenVO itripTokenVO = dto.getData();
                    String getToken1 = itripTokenVO.getToken();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(getToken, getToken1);
                    System.out.println("map====================" + map.get(getToken));
                    /* JSONArray.toJSONString(map);*/
                    jsonResult1 = JSON.parseObject(JSONArray.toJSONString(map));
                }
                System.out.println("strResult=" + str);
            } else {
                System.out.println("读取数据失败..");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult1;
    }

    @RequestMapping(value = "/index.html")
    public String index() {
        return "login/index";
    }

    @RequestMapping(value = "/getUserList.html")
    public String getUserList() {
        return "login/getUserList";
    }

    @RequestMapping(value = "/refrToken.html")
    public String refrToken() {
        return "login/refrToken";
    }

    @RequestMapping(value = "/validateToken.html")
    public String validateToken() {
        return "login/validateToken";
    }

    @RequestMapping(value = "/userlink.html")
    public String userlink() {
        return "userlink/index";
    }

    @RequestMapping(value = "/weixin.html")
    public String weixin() {
        return "weixinglogin/index";
    }

    @RequestMapping(value = "/useradd.html")
    public String userAdd() {
        return "userlink/userAdd";
    }
}
