package com.lizhuopeng.service;


import com.lizhuopeng.dao.UserDao;
import com.lizhuopeng.model.MiniPicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MiniPicUser user=userDao.getUserByUsername(s);
        if(user==null) throw new UsernameNotFoundException("用户名不存在");

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("guest");

        return new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),authorities);//目前数据库存了明文密码，将明文加密比较
    }
}
