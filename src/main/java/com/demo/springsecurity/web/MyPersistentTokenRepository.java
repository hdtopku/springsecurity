package com.demo.springsecurity.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.demo.springsecurity.domain.entity.PersistentLogins;
import com.demo.springsecurity.mapper.PersistentLoginsMapper;
import jakarta.annotation.Resource;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lxh
 * @Description
 * @createTime 2024-05-09 11:16:16
 */
@Component
public class MyPersistentTokenRepository implements PersistentTokenRepository {
    @Resource
    private PersistentLoginsMapper persistentLoginsMapper;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogins persistentLogins = new PersistentLogins();
        persistentLogins.setUsername(token.getUsername());
        persistentLogins.setSeries(token.getSeries());
        persistentLogins.setToken(token.getTokenValue());
        persistentLogins.setLastUsed(token.getDate());
        persistentLoginsMapper.insert(persistentLogins);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        LambdaUpdateWrapper<PersistentLogins> lambdaUpdateChainWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateChainWrapper.eq(PersistentLogins::getSeries, series).set(PersistentLogins::getToken, tokenValue).set(PersistentLogins::getLastUsed, lastUsed);
        persistentLoginsMapper.update(lambdaUpdateChainWrapper);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        LambdaQueryWrapper<PersistentLogins> queryWrapper = new LambdaQueryWrapper<PersistentLogins>().eq(PersistentLogins::getSeries, seriesId).last("LIMIT 1");
        PersistentLogins persistentLogins = persistentLoginsMapper.selectOne(queryWrapper);
        PersistentRememberMeToken token = null;
        if (persistentLogins != null) {
            token = new PersistentRememberMeToken(persistentLogins.getUsername(), persistentLogins.getSeries(), persistentLogins.getToken(), persistentLogins.getLastUsed());
        }
        return token;
    }

    @Override
    public void removeUserTokens(String username) {
        LambdaQueryWrapper<PersistentLogins> queryWrapper = new LambdaQueryWrapper<PersistentLogins>().eq(PersistentLogins::getUsername, username);
        persistentLoginsMapper.delete(queryWrapper);
    }
}
