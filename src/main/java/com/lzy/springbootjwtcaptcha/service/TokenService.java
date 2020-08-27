package com.lzy.springbootjwtcaptcha.service;

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
        String token="";
        // 将 user id 保存到 token 里面
        token= JWT.create().withAudience(user.getId(),user.getUsername(),user.getPower())
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
