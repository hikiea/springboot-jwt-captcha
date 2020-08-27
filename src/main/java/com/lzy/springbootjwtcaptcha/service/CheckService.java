package com.lzy.springbootjwtcaptcha.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.lzy.springbootjwtcaptcha.dao.User;
import com.lzy.springbootjwtcaptcha.dao.dto.UserLoginDTO;

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

    public Object checkLogin(UserLoginDTO userLoginInfo, HttpServletRequest request) {

        JSONObject jsonObject=new JSONObject();
        if (!userLoginInfo.getCode().equals(request.getSession().getAttribute("code"))){
            jsonObject.put("message","验证码错误");
            request.getSession().setAttribute("code",null);
            return jsonObject;
        }
        User userForBase=userService.findByUsername(userLoginInfo.getUsername());
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            request.getSession().setAttribute("code",null);
            return jsonObject;
        }else if(!userForBase.getPassword().equals(userLoginInfo.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                request.getSession().setAttribute("code",null);
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                log.info("用户：" + userLoginInfo.getUsername() + "登录");
                return jsonObject;
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
