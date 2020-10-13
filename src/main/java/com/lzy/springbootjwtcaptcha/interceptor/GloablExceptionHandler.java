package com.lzy.springbootjwtcaptcha.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.auth0.jwt.JWT;
import com.lzy.springbootjwtcaptcha.modules.base.model.entity.ResultDTO;
import com.lzy.springbootjwtcaptcha.modules.user.model.entity.RedisBlackToken;
import com.lzy.springbootjwtcaptcha.util.DateUtil;

/**
 * @author lizhongyi
 */
@ControllerAdvice
public class GloablExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultDTO handleException(Exception e) {
        String msg = e.getMessage();
        return ResultDTO.errorOf(500,msg);
    }
}
