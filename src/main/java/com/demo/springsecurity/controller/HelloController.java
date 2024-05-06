package com.demo.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @Description TODO
 * @createTime 2024-05-05 22:31:11
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/world")
    public String helloWorld() {
        return "Hello, World!";
    }
}
