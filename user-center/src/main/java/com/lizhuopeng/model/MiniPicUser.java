package com.lizhuopeng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class MiniPicUser implements Serializable {

    private int id;
    private String username;
    private String password;
    private boolean gender;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date updateTime;

    @JsonFormat(
            pattern = "yyyy年MM月dd日 HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;

    public MiniPicUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MiniPicUser() {
    }

    public User transferToSecurityUser(){
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("guest");
        return new User(this.username,this.password
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

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
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
