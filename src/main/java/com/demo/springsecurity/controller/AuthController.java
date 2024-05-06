package com.demo.springsecurity.controller;

import com.demo.springsecurity.domain.dto.LoginParams;
import com.demo.springsecurity.service.IUmsSysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 23:01:26
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private IUmsSysUserService umsSysUserService;

    @PostMapping
    public String login(@RequestBody LoginParams loginParams) {
        return umsSysUserService.login(loginParams.getUsername(), loginParams.getPassword());
    }
}
