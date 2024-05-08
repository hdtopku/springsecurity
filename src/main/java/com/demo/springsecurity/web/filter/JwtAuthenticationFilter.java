package com.demo.springsecurity.web.filter;

import com.demo.springsecurity.domain.entity.UmsSysUser;
import com.demo.springsecurity.web.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * @author lxh
 * @Description 1、获取到用户信息之后，需要将用户的信息告知SpringSecurity,SpringSecurity会去判断你访间的接口是否有相应的权限
 * 2、告知SpringSecurity 就是使用Authentication告知框架,SpringSecurity、会将信息存储到SecurityContext中--
 * 3、登录的时候，放置的数据是用户名和密码是要查找用的。后边请求，判断权限的时候，放置进去的数据是用户的信息，还有用户的权限，密码就不需要了
 * @createTime 2024-05-08 14:33:02
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            Claims claims = jwtUtils.parseToken(token);
            if (claims == null) {
                response.setCharacterEncoding("utf-8");
                response.setStatus(401);
                response.getWriter().write("token invalid");
                return;
            }
            UmsSysUser user = new UmsSysUser();
            user.setUsername(claims.get("username", String.class));
            user.setPerms(new HashSet<>(claims.get("perms", List.class)));
            user.setAvatar(claims.get("avatar", String.class));
            UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        doFilter(request, response, filterChain);
    }
}
