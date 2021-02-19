package com.lizhuopeng.Securityhandler;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.utils.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户登录成功信息返回，用户名称：{},时间:{}",user.getUsername(),new Date());
        WriteResponse.write(response, DataResult.success(user.getUsername() + "登陆成功"));

    }
}
