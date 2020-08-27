package com.lzy.springbootjwtcaptcha.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lzy.springbootjwtcaptcha.annotation.PassToken;
import com.lzy.springbootjwtcaptcha.dao.CheckCode;
import com.lzy.springbootjwtcaptcha.service.CheckCodeService;
import lombok.extern.slf4j.Slf4j;


/**
 * 获取验证码图片
 * @author lizhongyi
 */
@Controller
@RequestMapping("api")
@Slf4j
public class CheckCodeController {

    @Autowired
    private CheckCodeService checkCodeService;

    @PassToken
    @GetMapping("/code")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置长宽
            CheckCode checkCode = checkCodeService.generate(80, 28);
            String code = checkCode.getCode();
            System.out.println("当前验证码为:" + code);
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
            response.getOutputStream().write(checkCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
