package com.lizhuopeng.service;

import com.lizhuopeng.dao.UserDao;
import com.lizhuopeng.model.MiniPicUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class RegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 插入新用户
      * @param miniPicUser
     * @return 插入成功返回1
     */
    public boolean createUser(MiniPicUser miniPicUser){
        Date now = new Date();

        miniPicUser.setPassword(passwordEncoder.encode(miniPicUser.getPassword()));
        log.info("新用户[{}]密码已经加密",miniPicUser.getUsername());

        miniPicUser.setAccountNonExpired(true);
        miniPicUser.setAccountNonLocked(true);
        miniPicUser.setEnabled(true);
        miniPicUser.setCreateTime(now);
        miniPicUser.setUpdateTime(now);

        int result = userDao.createUser(miniPicUser);
        if(result==1) {
            log.info("新用户[{}]于时间[{}]新建成功",miniPicUser.getUsername(),new Date());
            return true;
        }
        else {
            log.warn("错误！新用户[{}]于时间[{}]新建失败",miniPicUser.getUsername(),new Date());
            return false;
        }
    }

    /**
     * 查询用户名是否重复，重复返回True
     * @param username
     * @return
     */
    public boolean checkUserNameDuplicate(String username){
        MiniPicUser user = userDao.getUserByUsername(username);
        return user != null;
    }
}
