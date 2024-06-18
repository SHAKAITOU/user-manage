package cn.caam.gs.auth;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class VerifyCodeController {

    @Autowired
    VerifyService verifyService;

    @GetMapping("/verify-code")
    public void getVerifyCodePng(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Cache-Control",
            "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");

    }
}