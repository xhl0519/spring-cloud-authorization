package com.auth.dao;

import com.auth.po.UserPO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户信息与数据交互持久层
 *
 * @author xhl
 * @Date 2020/7/27
 */
public interface UserDAO extends JpaRepository<UserPO,String> {

    /**
     * 根据用户名查询 数据库中唯一
     * @param username 用户名
     * @return
     */
    UserPO findByUsername(String username);

    /**
     * 查找角色
     * @param userId
     * @return
     */
    @Query(value = "select role_name from role r" +
            " left join user_middle_role m on m.role_id = r.id" +
            " left join user u on u.id = m.user_id" +
            " where u.id = :userId",nativeQuery = true)
    List<Object[]> findRoles(@Param("userId")String userId);
}
