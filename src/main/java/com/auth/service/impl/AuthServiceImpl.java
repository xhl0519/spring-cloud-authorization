package com.auth.service.impl;

import com.auth.dto.AuthToken;
import com.auth.service.AuthService;
import com.auth.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * 认证相关方法接口的实现类
 *
 * @author xhl
 * @Date 2020/7/27
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AuthToken getToken(String username, String password) {
        String url = "http://127.0.0.1:8080/oauth/token";
        LinkedMultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("client_id","user_center");
        body.add("client_secret","user_center");
        body.add("username",username);
        body.add("password",password);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(body,null);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        Map responseBody = response.getBody();
        if (responseBody == null || responseBody.get("access_token") == null
                || responseBody.get("refresh_token") == null || responseBody.get("jti") == null) {
            return null;
        }
        return new AuthToken((String)responseBody.get("access_token"),(String)responseBody.get("refresh_token"),(String)responseBody.get("jti"));
    }

    @Override
    public AuthToken refreshToken(String token) {
        Jedis jedis = RedisUtil.getJedis();
        String refreshToken = null;
        try{
            refreshToken = jedis.get("access_to_refresh:" + token);
        }finally {
            RedisUtil.close(jedis);
        }
        if (refreshToken == null) {
            return null;
        }
        String url = "http://127.0.0.1:8080/oauth/token";
        LinkedMultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","refresh_token");
        body.add("client_id","user_center");
        body.add("client_secret","user_center");
        body.add("refresh_token",refreshToken);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(body,null);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        Map responseBody = response.getBody();
        if (responseBody == null || responseBody.get("access_token") == null
                || responseBody.get("refresh_token") == null || responseBody.get("jti") == null) {
            return null;
        }
        return new AuthToken((String)responseBody.get("access_token"),(String)responseBody.get("refresh_token"),(String)responseBody.get("jti"));
    }

}
