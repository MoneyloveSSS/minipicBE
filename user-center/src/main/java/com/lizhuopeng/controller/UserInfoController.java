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
     * @return
     */
    @GetMapping("user/getBasicInfo")
    public DataResult getBasicInfo(){
        User login_user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MiniPicUser user = userInfoService.getUserBasicInfoByUsername(login_user.getUsername());
        log.info("查询用户[{}]的基本信息",login_user.getUsername());
        if(user== null){
            return new DataResult().setCode(RespCode.NOTFOUND.getCode()).setMessage("该用户不存在");
        }
        return DataResult.success(user);
    }



}
