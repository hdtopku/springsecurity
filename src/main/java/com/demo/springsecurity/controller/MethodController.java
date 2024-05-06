package com.demo.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 18:50:09
 */
@RestController
@Slf4j
public class MethodController {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/post/authorize")
    @PostAuthorize("returnObject.length() > 12")
    public String postAuthorize() {
        return "resultLength";
    }

    @GetMapping("/user/list")
    @PostFilter("filterObject.length()>5")
    public String[] userList() {
        return new String[]{"user1", "user22", "user33"};
    }

    @GetMapping("/add/users")
    @PreFilter("filterObject.length()>3")
    public List<String> addUsers(@RequestParam List<String> userList) {
        for (String user : userList) {
            log.info("user: {}", user);
        }
        return userList;
    }

    @GetMapping("/add/user")
    @PreFilter("filterObject.value.owner == authentication.name")
    public String addUser(@RequestParam String name) {
        return name;
    }

}
