package com.lzy.springbootjwtcaptcha.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.lzy.springbootjwtcaptcha.dao.BlackList;
import com.lzy.springbootjwtcaptcha.dao.RedisBlackToken;
import com.lzy.springbootjwtcaptcha.dao.User;
import com.lzy.springbootjwtcaptcha.dao.dto.ResultDTO;
import com.lzy.springbootjwtcaptcha.dao.dto.UserLoginDTO;
import com.lzy.springbootjwtcaptcha.mapper.UserMapper;
import com.lzy.springbootjwtcaptcha.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * @author lizhongyi
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CheckService checkService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DateService dateService;

    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    public ResultDTO getUserMessage(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject=new JSONObject();
        String token = httpServletRequest.getHeader("token");
        String username = JWT.decode(token).getAudience().get(1);
        String power = JWT.decode(token).getAudience().get(2);
        jsonObject.put("用户名：",username);
        jsonObject.put("当前权限：",power);
        return ResultDTO.successOf("用户获取成功！",jsonObject);
    }

    public ResultDTO getAllUserByAdmin(HttpServletRequest httpServletRequest) {
        if (checkService.checkPowerByAdmin(httpServletRequest)){
            List<User> users = userMapper.findUser();
            return ResultDTO.successOf("所有用户获取成功",users);
        }else{
            return ResultDTO.errorOf(500,"权限不足");
        }
    }

    public ResultDTO logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        RedisBlackToken blackToken = new RedisBlackToken();
        blackToken.setId(JWT.decode(token).getAudience().get(0));
        blackToken.setUsername(JWT.decode(token).getAudience().get(1));
        blackToken.setPower(JWT.decode(token).getAudience().get(2));
        blackToken.setTime(dateService.getNowDate());
        redisUtil.set(token,blackToken);
        log.info("用户：" + JWT.decode(token).getAudience().get(1) + "已登出");
        return ResultDTO.successOf("登出成功");
    }

}
