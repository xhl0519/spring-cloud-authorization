package com.auth.security;

import com.alibaba.fastjson.JSON;
import com.auth.dto.AuthToken;
import com.auth.dto.CustomUserDetails;
import com.auth.service.AuthService;
import com.auth.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功逻辑
 *
 * @author xhl
 * @Date 2020/7/27
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/json;charset=utf-8");
        String password = request.getParameter("password");
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        AuthToken token = authService.getToken(userDetails.getUsername(), password);
        response.getWriter().write(JSON.toJSONString(R.ok().message("登录成功").data("token",token.getAccess_token())));
    }
}
