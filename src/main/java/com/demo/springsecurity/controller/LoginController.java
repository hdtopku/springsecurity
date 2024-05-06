package com.demo.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 12:35:42
 */
@Controller
public class LoginController {
    @GetMapping("/to_login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
