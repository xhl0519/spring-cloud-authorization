package com.auth.controller;

import com.auth.po.UserPO;
import com.auth.service.UserService;
import com.auth.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;

/**
 * 用户相关api接口
 *
 * @author xhl
 * @Date 2020/7/27
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("list")
    public R list() {
        List<UserPO> list = userService.list();
        return R.ok().data("list",list);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("get/{id}")
    public R getById(@PathVariable("id")String id) {
        UserPO userPO = userService.getById(id);
        if (userPO == null) {
            return R.error().message("信息不存在");
        }
        return R.ok().data("user",userPO);
    }

    /**
     * 注销接口
     * @param request
     * @return
     */
    @PostMapping("logout")
    public R logout(HttpServletRequest request) {
        String token = request.getHeader("authorization").replace("Bearer ","");
        boolean bool = consumerTokenServices.revokeToken(token);
        if (bool) {
            return R.ok().message("登出成功");
        }
        return R.error().message("登出失败");
    }
}
