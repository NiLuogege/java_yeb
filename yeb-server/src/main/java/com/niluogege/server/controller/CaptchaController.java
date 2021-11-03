package com.niluogege.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.niluogege.server.config.security.component.CaptchaConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 */

@Api(tags = "LoginController")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "获取验证码")
    @RequestMapping(produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        //下面代码设置 response 输出类型为 image/jpeg
        response.setDateHeader("Expires", 0);
        //set 默认 http/1.1 不使用缓存
        response.setHeader("Cache-Control", "no-store, no-chche, must-revalidate");
        //set IE http/1.1 不使用缓存
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        //set 默认 http/1.0 不使用缓存
        response.setHeader("Pragma", "no-cache");
        //set response 输出类型为 image/jpeg
        response.setContentType("image/jpeg");
        //-------------------生成验证码 begin --------------------------
        //获取验证码 文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容" + text);
        //将验证码放进session中
        request.getSession().setAttribute("captcha", text);
        //生成图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //-------------------生成验证码 end --------------------------
    }

}
