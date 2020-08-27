package com.lzy.springbootjwtcaptcha.dao.dto;

import lombok.Data;

/**
 * @author lizhongyi
 */
@Data
public class UserLoginDTO {

    private String id;

    private String username;

    private String password;

    private String code;

}
