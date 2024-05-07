package com.demo.springsecurity.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.springsecurity.domain.entity.UmsSysUser;
import com.demo.springsecurity.mapper.UmsSysUserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-07 08:35:59
 */
@Service
@Slf4j
public class UmsSysUserDetailsService implements UserDetailsService {
    @Resource
    private UmsSysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = sysUserMapper.selectOne(new QueryWrapper<UmsSysUser>().lambda().eq(UmsSysUser::getUsername, username));
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户不存在：" + username);
        }
        return userDetails;
    }
}
