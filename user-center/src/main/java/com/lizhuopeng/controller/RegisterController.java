package com.lizhuopeng.controller;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.entities.RespCode;
import com.lizhuopeng.model.MiniPicUser;
import com.lizhuopeng.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    /**
     * 注册新用户
     * @param miniPicUser
     * @return 成功返回 0 ,重复返回 444+该用户名已存在 ，插入数据库失败返回 500+新建失败，请重试
     */
    @PostMapping(value = "/user/register")
    public DataResult registerUser(@RequestBody MiniPicUser miniPicUser) {
        log.info("用户名[{}]查重",miniPicUser.getUsername());
        if(registerService.checkUserNameDuplicate(miniPicUser.getUsername())){
            log.info("用户名[{}]已经被注册",miniPicUser.getUsername());
            return new DataResult().setCode(RespCode.INVALID_PARAM.getCode()).setMessage("该用户名已存在");
        }
        log.info("准备新建用户[{}]",miniPicUser.getUsername());
        boolean result = registerService.createUser(miniPicUser);
        if(result){
            return DataResult.success(miniPicUser.getUsername()+"新建成功");
        }
        return DataResult.serverError("新建失败，请重试");
    }

}
