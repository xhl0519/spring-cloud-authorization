package com.auth.dto;

/**
 * 自定义封装TOKEN类
 *
 * @author xhl
 * @Date 2020/7/27
 */
public class AuthToken {

    private String access_token;

    private String refresh_token;

    private String jti;

    public AuthToken() {
    }

    public AuthToken(String access_token, String refresh_token, String jti) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.jti = jti;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}
