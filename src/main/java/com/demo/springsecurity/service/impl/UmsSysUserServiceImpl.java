package com.demo.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.springsecurity.domain.entity.UmsSysUser;
import com.demo.springsecurity.mapper.UmsSysUserMapper;
import com.demo.springsecurity.service.IUmsSysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 22:22:23
 */
@Service
@Slf4j
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        try {
            authenticationManager.authenticate(authenticationRequest);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            log.error("登录失败：{}", e.getMessage());
            return "用户名或密码错误";
        } catch (LockedException e) {
            return "账户已锁定，请联系管理员";
        } catch (DisabledException e) {
            return "账户已禁用，请联系管理员";
        } catch (AccountExpiredException e) {
            return "账户已过期，请联系管理员";
        } catch (CredentialsExpiredException e) {
            return "密码已过期，请联系管理员";
        } catch (Exception e) {
            return String.format("登录失败：%s", e.getMessage());
        }
        return UUID.randomUUID().toString().replace("-", "");
    }
}
