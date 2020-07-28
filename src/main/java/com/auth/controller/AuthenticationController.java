package com.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth.dto.AuthToken;
import com.auth.service.AuthService;
import com.auth.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证相关api接口
 *
 * @author xhl
 * @Date 2020/7/27
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    /**
     * 刷新token
     * @param json
     * @return
     */
    @PostMapping("refresh/token")
    public R refreshToken(@RequestBody String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        AuthToken authToken = authService.refreshToken(jsonObject.getString("token"));
        if (authToken == null) {
            return R.error().message("刷新失败");
        }
        return R.ok().message("刷新成功").data("token",authToken.getAccess_token());
    }

}
