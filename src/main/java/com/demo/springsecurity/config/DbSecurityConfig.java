package com.demo.springsecurity.config;

import com.demo.springsecurity.web.UmsSysUserDetailsService;
import com.demo.springsecurity.web.filter.JwtAuthenticationFilter;
import com.demo.springsecurity.web.manager.MyAuthorizationManager;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-07 07:50:21
 */
//@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class DbSecurityConfig {
    @Resource
    private UmsSysUserDetailsService userDetailsService;
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Resource
    private MyAuthorizationManager myAuthorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/auth/login").permitAll()
                        .anyRequest().access(myAuthorizationManager));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
