package com.lzy.springbootjwtcaptcha.modules.checkCode.model.entity;

import lombok.Data;

/**
 * @author lizhongyi
 */
@Data
public class CheckCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;

}
