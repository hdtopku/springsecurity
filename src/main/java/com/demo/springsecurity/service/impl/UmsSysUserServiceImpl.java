package com.demo.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.springsecurity.domain.entity.UmsSysUser;
import com.demo.springsecurity.mapper.UmsSysUserMapper;
import com.demo.springsecurity.service.IUmsSysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 22:22:23
 */
@Service
@Slf4j
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {

    @Override
    public String login(String username, String password) {

        return "";
    }
}
