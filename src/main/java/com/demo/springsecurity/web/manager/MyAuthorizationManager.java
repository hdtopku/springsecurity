package com.demo.springsecurity.web.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.springsecurity.domain.entity.UmsMenu;
import com.demo.springsecurity.domain.entity.UmsSysUser;
import com.demo.springsecurity.mapper.UmsMenuMapper;
import com.mysql.cj.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-08 23:08:22
 */
@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    @Resource
    private UmsMenuMapper menuMapper;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext request) {
        String uri = request.getRequest().getRequestURI().replaceFirst("/", "");
        UmsSysUser user = (UmsSysUser) authentication.get().getPrincipal();
        if ("/auth/login".equals(uri) || "/logout".equals(uri) || "error".equals(uri)) {
            return new AuthorizationDecision(true);
        }
        UmsMenu umsMenu = menuMapper.selectOne(new QueryWrapper<UmsMenu>().lambda().eq(UmsMenu::getPath, uri));
        if (umsMenu == null) {
            return new AuthorizationDecision(false);
        }
        String menuPerm = umsMenu.getPerms();
        if (StringUtils.isEmptyOrWhitespaceOnly(menuPerm)) {
            return new AuthorizationDecision(true);
        }
        for (GrantedAuthority authority : user.getAuthorities()) {
            String userPerm = authority.getAuthority();
            if (menuPerm.equals(userPerm)) {
                return new AuthorizationDecision(true);
            }
        }
        return new AuthorizationDecision(false);
    }
}
