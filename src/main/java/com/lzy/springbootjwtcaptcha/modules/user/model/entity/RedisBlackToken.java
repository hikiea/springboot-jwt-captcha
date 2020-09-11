package com.lzy.springbootjwtcaptcha.modules.user.model.entity;

import lombok.Data;

/**
 * @author lizhongyi
 */
@Data
public class RedisBlackToken {

    private String id;

    private String username;

    private String power;

    private String time;

}
