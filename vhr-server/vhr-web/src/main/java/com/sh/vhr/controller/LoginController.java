package com.sh.vhr.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    Producer producer;

    @GetMapping("/verifyCode")
    @ResponseBody
    public void verifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        String text = producer.createText();
        // 将验证码保存到 session
        session.setAttribute("verify_code", text);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
        }
    }
}
