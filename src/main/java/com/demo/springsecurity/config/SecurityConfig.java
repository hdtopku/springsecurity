package com.demo.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author lxh
 * @Description 使用的是 SpringSecutiry 的默认配置，不需要额外配置
 * 1、该类不再需要继承 Security定义的类
 * 2、需要使用@Configuration才会被 Spring 容器加载
 * 3、废弃了很多方法，比如 and()方法，建议使用 Lambda 表示实现
 * @createTime 2024-05-06 08:26:13
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // 禁用CSRF
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/hello/admin").hasRole("admin")
                        .requestMatchers("/hello/user").hasRole("user")
                        .requestMatchers("/to_login", "*/*.js").permitAll()
                        .anyRequest().authenticated());
        http.formLogin(form -> form.loginPage("/to_login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
//        定义用户信息
        UserDetails userDetails = User.withUsername("admin").password("$2a$10$MWGKcUtZU/Cg5A.XamKyEuEMnebenPwho4WP2tdV8j.GgKUvrOv4u") // {noop}表示明文密码
                .roles("admin", "user").build();
        UserDetails userDetails2 = User.withUsername("user").password("$2a$10$MWGKcUtZU/Cg5A.XamKyEuEMnebenPwho4WP2tdV8j.GgKUvrOv4u").roles("user").build();
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(userDetails);
        inMemoryUserDetailsManager.createUser(userDetails2);
        return inMemoryUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
