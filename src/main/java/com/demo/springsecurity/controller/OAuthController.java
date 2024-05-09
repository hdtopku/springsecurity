package com.demo.springsecurity.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.demo.springsecurity.domain.entity.GiteeBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-09 17:31:38
 */
@RestController
@RequestMapping("/oauth")
public class OAuthController {
    @Value("${spring.security.oauth2.client.registration.gitee.client-id}")
    private String giteeClientId;
    @Value("${spring.security.oauth2.client.registration.gitee.client-secret}")
    private String giteeClientSecret;
    @Value("${spring.security.oauth2.client.registration.gitee.redirect-uri}")
    private String giteeRedirectUri;
    @Value("${spring.security.oauth2.client.provider.gitee.token-uri}")
    private String giteeTokenUri;
    @Value("${spring.security.oauth2.client.provider.gitee.user-info-uri}")
    private String giteeUserInfoUri;


    @GetMapping("/notify")
    public String giteeNotify(@RequestParam("code") String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", giteeRedirectUri);
        params.put("client_id", giteeClientId);
        params.put("client_secret", giteeClientSecret);
        String post = HttpUtil.post(giteeTokenUri, params);
        GiteeBody giteeBody = JSONUtil.toBean(post, GiteeBody.class);
        return HttpUtil.get(giteeUserInfoUri + "?access_token=" + giteeBody.getAccessToken());
    }

}
