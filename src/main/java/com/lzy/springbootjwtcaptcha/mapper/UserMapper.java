package com.lzy.springbootjwtcaptcha.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.lzy.springbootjwtcaptcha.dao.User;

/**
 * @author jinbin
 * @date 2018-07-08 20:44
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
