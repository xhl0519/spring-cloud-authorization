package com.auth.service.impl;

import com.auth.dao.UserDAO;
import com.auth.dto.CustomUserDetails;
import com.auth.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义用户信息服务实现类
 *
 * @author xhl
 * @Date 2020/7/27
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userDAO.findByUsername(username);
        if (userPO == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<Object[]> roles = userDAO.findRoles(userPO.getId());
        String permission = null;
        if (roles != null && roles.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < roles.size(); i++) {
                if (i != roles.size() - 1) {
                    sb.append("ROLE_" + roles.get(i)[0].toString() + ",");
                } else {
                    sb.append("ROLE_" + roles.get(i)[0].toString());
                }
            }
            permission = sb.toString();
        }
        return new CustomUserDetails(userPO.getId(),userPO.getUsername(),userPO.getPassword(),permission);
    }

}
