package com.lizhuopeng.service;

import com.lizhuopeng.dao.UserDao;
import com.lizhuopeng.model.MiniPicUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoService {
    @Autowired
    private UserDao userDao;

    /**
     * 得到用户基本信息
     * @param username
     * @return username,gender,createTime
     */
    public MiniPicUser getUserBasicInfoByUsername(String username){
        MiniPicUser user = userDao.getUserBasicInfoByUsername(username);
        if(user==null) {
            log.warn("该登录用户的基本信息不存在，用户名为[{}]",username);
        }
        return user;
    }
}
