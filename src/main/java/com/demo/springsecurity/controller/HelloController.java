package com.demo.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-05 22:31:11
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello, Admin!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER:READ')")
    public String helloUser() {
        return "Hello, User!";
    }

    @GetMapping("/user/vip")
    @PreAuthorize("hasAuthority('VIP')")
    public String helloUserVip() {
        return "Hello, User VIP!";
    }
}
