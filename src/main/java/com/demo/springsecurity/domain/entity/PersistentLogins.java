package com.demo.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-09 11:05:07
 */
@Data
@TableName("persistent_logins")
public class PersistentLogins {
    private String username;
    private String series;
    private String token;
    private Date lastUsed;
}
