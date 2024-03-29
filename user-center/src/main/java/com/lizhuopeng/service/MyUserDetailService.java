package com.lizhuopeng.service;


import com.lizhuopeng.dao.UserDao;
import com.lizhuopeng.model.MiniPicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /**
     * 查询数据库用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MiniPicUser user=userDao.getUserByUsername(s);
        if(user==null) throw new UsernameNotFoundException(s+"用户名不存在");

        return user.transferToSecurityUser();
    }

}
