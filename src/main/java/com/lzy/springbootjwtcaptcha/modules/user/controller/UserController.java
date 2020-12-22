package com.lzy.springbootjwtcaptcha.modules.user.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lzy.springbootjwtcaptcha.annotation.UserLoginToken;
import com.lzy.springbootjwtcaptcha.modules.base.model.entity.ResultDTO;
import com.lzy.springbootjwtcaptcha.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.springbootjwtcaptcha.modules.user.service.CheckService;
import com.lzy.springbootjwtcaptcha.modules.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lizhongyi
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckService checkService;

    @PostMapping("/login")
    @CrossOrigin
    public ResultDTO login(@RequestBody UserLoginDTO userLoginInfo){
        ResultDTO resultDTO = checkService.checkLogin(userLoginInfo);
        return resultDTO;
    }
    
    @UserLoginToken
    @GetMapping("/user")
    public ResultDTO getUserMessage(HttpServletRequest httpServletRequest){
        ResultDTO userMessage = userService.getUserMessage(httpServletRequest);
        return userMessage;
    }

    @UserLoginToken
    @GetMapping("/user/all")
    public ResultDTO getAllUserByAdmin(HttpServletRequest httpServletRequest){
        ResultDTO allUserByAdmin = userService.getAllUserByAdmin(httpServletRequest);
        return allUserByAdmin;
    }

    @UserLoginToken
    @GetMapping("/logout")
    public ResultDTO logout(HttpServletRequest httpServletRequest){
        ResultDTO logout = userService.logout(httpServletRequest);
        return logout;
    }

}
