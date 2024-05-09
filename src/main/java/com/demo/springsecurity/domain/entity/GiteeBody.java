package com.demo.springsecurity.domain.entity;

import lombok.Data;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-09 19:04:00
 */
@Data
public class GiteeBody {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
}
