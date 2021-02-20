package com.lizhuopeng.dao;

import com.lizhuopeng.model.MiniPicUser;

public interface UserDao {
    /**
     * 根据用户名得到用户
     * @param username
     * @return
     */
    MiniPicUser getUserByUsername(String username);

    /**
     *
     * 创建用户
     * @param miniPicUser
     * @return
     */
    int createUser (MiniPicUser miniPicUser);
}
