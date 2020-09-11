package com.lzy.springbootjwtcaptcha.modules.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.lzy.springbootjwtcaptcha.modules.user.model.entity.User;


/**
 * @author lizhongyi
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user where username=#{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user where id=#{id}")
    User findUserById(String id);

    @Select("select * from user")
    List<User> findUser();
}
