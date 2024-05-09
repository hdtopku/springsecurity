package com.demo.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-09 08:59:09
 */
@RestController
@RequestMapping("/sys/employee")
public class EmployeeController {
    @GetMapping("/list")
    public String getEmployeeList() {
        return "Employee List";
    }
}
