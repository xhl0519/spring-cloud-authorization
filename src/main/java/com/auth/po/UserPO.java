package com.auth.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户实体类
 *
 * @author xhl
 * @Date 2020/7/27
 */
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "user")
public class UserPO {

    /** ID */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    /** 用户名 */
    @Column(name = "username")
    private String username;

    /** 密码 */
    @Column(name = "password")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
