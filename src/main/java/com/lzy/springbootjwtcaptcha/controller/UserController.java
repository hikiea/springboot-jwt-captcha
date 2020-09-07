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
import com.lzy.springbootjwtcaptcha.annotation.UserLoginToken;
import com.lzy.springbootjwtcaptcha.dao.dto.ResultDTO;
import com.lzy.springbootjwtcaptcha.dao.dto.UserLoginDTO;
import com.lzy.springbootjwtcaptcha.service.CheckService;
import com.lzy.springbootjwtcaptcha.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lizhongyi
 */
@RestController
@RequestMapping("api")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckService checkService;

    @PostMapping("/login")
    public ResultDTO login(@RequestBody UserLoginDTO userLoginInfo, HttpServletRequest request){
        ResultDTO resultDTO = checkService.checkLogin(userLoginInfo, request);
        return resultDTO;
    }
    
    @UserLoginToken
    @GetMapping("/user")
    public Object getUserMessage(HttpServletRequest httpServletRequest){
        ResultDTO userMessage = userService.getUserMessage(httpServletRequest);
        return userMessage;
    }

    @UserLoginToken
    @GetMapping("/user/all")
    public Object getAllUserByAdmin(HttpServletRequest httpServletRequest){
        ResultDTO allUserByAdmin = userService.getAllUserByAdmin(httpServletRequest);
        return allUserByAdmin;
    }

}
