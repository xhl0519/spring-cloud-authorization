package com.auth.service.impl;

import com.auth.dao.UserDAO;
import com.auth.po.UserPO;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户相关功能服务层具体实现
 *
 * @author xhl
 * @Date 2020/7/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int addUser(UserPO userPO) {
        UserPO user = userDAO.findByUsername(userPO.getUsername());
        if (user != null) {
            return 0;
        }
        userPO.setPassword(passwordEncoder.encode(userPO.getPassword()));
        userDAO.save(userPO);
        return 1;
    }

    @Override
    public List<UserPO> list() {
        return userDAO.findAll();
    }

    @Override
    public UserPO getById(String id) {
        Optional<UserPO> optional = userDAO.findById(id);
        return optional.orElse(null);
    }


}
