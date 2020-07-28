package com.auth.service;

import com.auth.dto.AuthToken;

/**
 * 认证相关方法接口
 */
public interface AuthService {

    /**
     * 获取token
     * @param username
     * @param password
     * @return
     */
    AuthToken getToken(String username,String password);

    /**
     * 刷新token
     * @param token
     * @return
     */
    AuthToken refreshToken(String token);
}
