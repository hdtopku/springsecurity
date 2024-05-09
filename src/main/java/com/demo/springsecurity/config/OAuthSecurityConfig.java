package com.demo.springsecurity.config;

import com.demo.springsecurity.web.UmsSysUserDetailsService;
import com.demo.springsecurity.web.handler.MyLogoutSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-09 17:25:56
 */
@Configuration
@EnableWebSecurity
public class OAuthSecurityConfig {
    @Resource
    private UmsSysUserDetailsService userDetailsService;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/oauth/notify").permitAll()
                .anyRequest().authenticated());
//        http.formLogin(form->form.loginPage("/to_login"));
        http.oauth2Login(Customizer.withDefaults());
        http.logout(logout -> logout.logoutSuccessHandler(myLogoutSuccessHandler).deleteCookies("rememberMe"));
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
