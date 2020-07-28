package com.auth.service;

import com.auth.po.UserPO;

import java.util.List;

/**
 * 用户相关服务接口定义
 *
 * @author xhl
 * @Date 2020/7/27
 */
public interface UserService {

    /**
     * 添加用户
     * @param userPO
     * @return 0 用户已存在 1 注册成功
     */
    int addUser(UserPO userPO);

    /**
     * 获取用户列表
     * @return
     */
    List<UserPO> list();

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    UserPO getById(String id);
}
