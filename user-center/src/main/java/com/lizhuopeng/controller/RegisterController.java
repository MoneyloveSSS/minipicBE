package com.lizhuopeng.controller;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.model.MiniPicUser;
import com.lizhuopeng.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/register")
    public DataResult registerUser(@RequestBody MiniPicUser miniPicUser) {
        log.info("准备新建用户[{}]",miniPicUser.getUsername());
        boolean result = registerService.createUser(miniPicUser);
        if(result){
            return DataResult.success(miniPicUser.getUsername()+"新建成功");
        }
        return DataResult.serverError("新建失败，请重试");
    }
}
