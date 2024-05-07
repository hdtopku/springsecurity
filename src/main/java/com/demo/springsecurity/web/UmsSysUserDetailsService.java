package com.demo.springsecurity.web;

import com.demo.springsecurity.domain.entity.UmsMenu;
import com.demo.springsecurity.domain.entity.UmsRole;
import com.demo.springsecurity.domain.entity.UmsSysUser;
import com.demo.springsecurity.mapper.UmsMenuMapper;
import com.demo.springsecurity.mapper.UmsSysUserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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
    @Resource
    private UmsMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        做用户信息查询，尽量不要多次访问数据库，尽量一次查出需要的数据，多表查询不要超过 3 张表
//        1、查询用户角色信息
        UmsSysUser umsSysUser = sysUserMapper.selectUserByUsername(username);
        if (umsSysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
//        2、查询用户权限信息
        Set<UmsRole> roleSet = umsSysUser.getRoleSet();
        Set<Long> roleIds = roleSet.stream().map(UmsRole::getRoleId).collect(Collectors.toSet());
        Set<UmsMenu> menuSet = menuMapper.selectMenuByRoleIds(roleIds);
        Set<String> perms = umsSysUser.getPerms();
        perms.addAll(menuSet.stream().map(UmsMenu::getPerms).collect(Collectors.toSet()));
        return umsSysUser;
    }
}
