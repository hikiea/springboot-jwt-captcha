package com.lzy.springbootjwtcaptcha.modules.user.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.lzy.springbootjwtcaptcha.modules.user.model.entity.User;
import com.lzy.springbootjwtcaptcha.modules.base.model.entity.ResultDTO;
import com.lzy.springbootjwtcaptcha.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.springbootjwtcaptcha.modules.jwt.service.TokenService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lizhongyi
 */
@Slf4j
@Service
public class CheckService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public ResultDTO checkLogin(UserLoginDTO userLoginInfo, HttpServletRequest request) {

        JSONObject jsonObject=new JSONObject();
        if (!userLoginInfo.getCode().equals(request.getSession().getAttribute("code"))){
            request.getSession().setAttribute("code",null);
            return ResultDTO.errorOf(500,"验证码错误");
        }
        User userForBase=userService.findByUsername(userLoginInfo.getUsername());
        if(userForBase==null){
            request.getSession().setAttribute("code",null);
            return ResultDTO.errorOf(500,"登录失败，用户不存在");
        }else if(!userForBase.getPassword().equals(userLoginInfo.getPassword())){
            request.getSession().setAttribute("code",null);
            return ResultDTO.errorOf(500,"登录失败，密码错误");
        }else {
            request.getSession().setAttribute("code",null);
            String token = tokenService.getToken(userForBase);
            jsonObject.put("token", token);
            jsonObject.put("user", userForBase);
            log.info("用户：" + userLoginInfo.getUsername() + "登录");
            return ResultDTO.successOf("登录成功",jsonObject);
        }
    }

    public boolean checkPowerByAdmin(HttpServletRequest request){
        String token = request.getHeader("token");
        String power = JWT.decode(token).getAudience().get(2);
        if ("admin".equals(power)){
            return true;
        } else {
            return false;
        }
    }
}
