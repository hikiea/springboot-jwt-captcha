package com.lzy.springbootjwtcaptcha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lzy.springbootjwtcaptcha.dao.User;
import com.lzy.springbootjwtcaptcha.mapper.UserMapper;


/**
 * @author lizhongyi
 */
@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    public List<User> findUser(){
        return userMapper.findUser();
    }

}
