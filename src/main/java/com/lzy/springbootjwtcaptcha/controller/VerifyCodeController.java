package com.lzy.springbootjwtcaptcha.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lzy.springbootjwtcaptcha.dao.VerifyCode;
import com.lzy.springbootjwtcaptcha.mapper.impl.IVerifyCodeMapperImpl;


/**
 * 获取验证码图片
 * @author lizhongyi
 */
@Controller
@RequestMapping("api")
public class VerifyCodeController {

    @GetMapping("/code")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeMapperImpl iVerifyCodeGen = new IVerifyCodeMapperImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            System.out.println(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("code", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
