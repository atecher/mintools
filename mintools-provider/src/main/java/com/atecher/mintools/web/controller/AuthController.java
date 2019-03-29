package com.atecher.mintools.web.controller;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: hanhongwei
 * @date: 2019/3/29 11:01 AM
 */
@RestController
public class AuthController {
    @GetMapping("/google/auth/code")
    public String getGoogleCode(String scret){
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        int code = gAuth.getTotpPassword(scret);
        String format = String.format("%06d", code);
        return format;
    }
}
