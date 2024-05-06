package com.demo.springsecurity.domain.dto;

import lombok.Data;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 23:02:55
 */
@Data
public class LoginParams implements java.io.Serializable {
    private String username;
    private String password;
}
