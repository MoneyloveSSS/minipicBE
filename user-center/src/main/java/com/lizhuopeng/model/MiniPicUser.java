package com.lizhuopeng.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;


public class MiniPicUser {

    private int id;
    private String username;
    private String password;
    private int gender;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;
    private Date updateTime;
    private Date createTime;

    public MiniPicUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MiniPicUser() {
    }

    public User transferToSecurityUser(){
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("guest");
        /* 目前数据库存了明文密码，将明文加密比较*/
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return new User(this.username,bCryptPasswordEncoder.encode(this.password)
                , this.enabled, this.accountNonExpired,true, this.accountNonLocked,authorities);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}