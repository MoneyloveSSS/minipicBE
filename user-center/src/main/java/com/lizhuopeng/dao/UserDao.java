package com.lizhuopeng.dao;

import com.lizhuopeng.model.MiniPicUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    /**
     * 根据用户名得到用户
     * @param username
     * @return
     */
    MiniPicUser getUserByUsername(String username);

    /**
     * 根据用户名得到用户基础信息
     * @param username
     * @return
     */
    MiniPicUser getUserBasicInfoByUsername(String username);

    /**
     *
     * 创建用户
     * @param miniPicUser
     * @return
     */
    int createUser (MiniPicUser miniPicUser);

}
