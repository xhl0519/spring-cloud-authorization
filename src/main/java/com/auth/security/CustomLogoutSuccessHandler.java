package com.auth.security;

import com.alibaba.fastjson.JSON;
import com.auth.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登出成功逻辑
 *
 * @author xhl
 * @Date 2020/7/27
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        response.setContentType("text/json;charset=utf-8");
        boolean bool = consumerTokenServices.revokeToken(token);
        if (bool) {
            response.getWriter().write(JSON.toJSONString(R.ok().message("登出成功")));
        } else {
            response.getWriter().write(JSON.toJSONString(R.error().message("登出失败")));
        }
    }

}
