package com.lizhuopeng.service;

import com.lizhuopeng.dao.UserDao;
import com.lizhuopeng.model.MiniPicUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class RegisterService {

    @Autowired
    private UserDao userDao;

    /**
     * 插入新用户
      * @param miniPicUser
     * @return
     */
    public boolean createUser(MiniPicUser miniPicUser){
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
}
