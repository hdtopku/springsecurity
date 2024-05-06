package com.demo.springsecurity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.springsecurity.domain.entity.UmsSysUser;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 22:19:56
 */
public interface IUmsSysUserService extends IService<UmsSysUser> {
    String login(String username, String password);
}
