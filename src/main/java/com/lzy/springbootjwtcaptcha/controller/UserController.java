package com.lzy.springbootjwtcaptcha.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.lzy.springbootjwtcaptcha.annotation.PassToken;
import com.lzy.springbootjwtcaptcha.annotation.UserLoginToken;
import com.lzy.springbootjwtcaptcha.dao.dto.UserLoginDTO;
import com.lzy.springbootjwtcaptcha.service.CheckService;
import com.lzy.springbootjwtcaptcha.service.TokenService;
import com.lzy.springbootjwtcaptcha.service.UserService;

/**
 * @author jinbin
 * @date 2018-07-08 20:45
 */
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CheckService checkService;

    @PostMapping("/login")
    public Object login(@RequestBody UserLoginDTO userLoginInfo, HttpServletRequest request){
        Object result = checkService.checkLogin(userLoginInfo, request);
        return result;
    }
    
    @UserLoginToken
    @GetMapping("/getUser")
    public Object getUserMessage(HttpServletRequest httpServletRequest){
        JSONObject jsonObject=new JSONObject();
        String token = httpServletRequest.getHeader("token");
        String username = JWT.decode(token).getAudience().get(1);
        String power = JWT.decode(token).getAudience().get(2);
        jsonObject.put("message","登录成功");
        jsonObject.put("用户名：",username);
        jsonObject.put("当前权限：",power);
        return jsonObject;
    }

    @UserLoginToken
    @GetMapping("/getAllUser")
    public Object getAllUserByAdmin(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        boolean b = checkService.checkPowerByAdmin(request);
        if (b){
            return userService.findUser();
        }else{
            jsonObject.put("message","权限不足");
            return jsonObject;
        }
    }

    @PassToken
    @GetMapping("/getNewsNotLogin")
    public String unlimited(){
        return "这个消息不需要权限";
    }
}
