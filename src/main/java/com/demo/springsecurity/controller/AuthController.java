package com.demo.springsecurity.controller;

import com.demo.springsecurity.domain.dto.LoginParams;
import com.demo.springsecurity.service.IUmsSysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginParams loginParams) {
        String token = umsSysUserService.login(loginParams.getUsername(), loginParams.getPassword());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("code", 200);
        map.put("msg", "success");
        return map;
    }
}
