package com.lzy.springbootjwtcaptcha.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.lzy.springbootjwtcaptcha.dao.dto.ResultDTO;

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
