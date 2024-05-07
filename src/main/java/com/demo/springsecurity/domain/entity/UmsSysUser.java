package com.demo.springsecurity.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-06 21:43:35
 */
@Data
@TableName("ums_sys_user")
public class UmsSysUser implements java.io.Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Integer sex;
    private String avatar;
    private String password;
    private Integer status;
    private Long creator;
    private Long updater;
    private String createTime;
    private String updateTime;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    private String remark;

    //    角色信息
    private Set<UmsRole> roleSet = new HashSet<>();
    //    权限信息
    private Set<String> perms = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perms.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
