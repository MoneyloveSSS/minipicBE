package com.lizhuopeng.controller;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.entities.RespCode;
import com.lizhuopeng.model.MiniPicUser;
import com.lizhuopeng.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据用户名得到用户基础信息
      * @param username
     * @return username,gender,createTime
     */
    @GetMapping("user/getBasicInfo")
    public DataResult getBasicInfo(@RequestParam("username") String username){
        //验证是否查询的是本人账户的信息
        User login_user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!username.equals(login_user.getUsername())){
            log.warn("用户名[{}]正查询不属于自己的账号信息[{}]",login_user.getUsername(),username);
            return DataResult.serverError("非法查询");
        }
        MiniPicUser user = userInfoService.getUserBasicInfoByUsername(username);
        log.info("查询用户[{}]的基本信息",username);
        if(user== null){
            return new DataResult().setCode(RespCode.NOTFOUND.getCode()).setMessage("该用户不存在");
        }
        return DataResult.success(user);
    }



}
