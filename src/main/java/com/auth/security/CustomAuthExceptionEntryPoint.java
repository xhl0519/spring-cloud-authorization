package com.auth.security;

import com.alibaba.fastjson.JSON;
import com.auth.util.R;
import com.auth.util.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义无效TOKEN异常返回
 *
 * @author xhl
 * @Date 2020/7/28
 */
@Component
public class CustomAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(R.setResult(ResultCodeEnum.INVALID_TOKEN)));
    }
}
