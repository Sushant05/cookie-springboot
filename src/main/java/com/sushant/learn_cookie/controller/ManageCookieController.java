package com.sushant.learn_cookie.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cookie")
public class ManageCookieController {


    @GetMapping("/add")
    public ResponseEntity<String> addCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("sessionId", UUID.randomUUID().toString());
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.ok("Cookie added successfully");
    }

    @GetMapping("/read")
    public ResponseEntity<String> readCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    return ResponseEntity.ok("Hello, " + cookie.getValue());
                }
            }
        }
        return ResponseEntity.ok("No cookie found");
    }

}
