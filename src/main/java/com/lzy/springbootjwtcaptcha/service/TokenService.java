package com.lzy.springbootjwtcaptcha.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lzy.springbootjwtcaptcha.dao.User;


/**
 * @author lizhongyi
 */
@Service("TokenService")
public class TokenService {


    public String getToken(User user) {

        String uuid = UUID.randomUUID().toString();

        String token="";
        // 将 需要的信息 保存到 token 里面
        token= JWT.create().withAudience(user.getId(),user.getUsername(),user.getPower(),uuid)
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }
}
