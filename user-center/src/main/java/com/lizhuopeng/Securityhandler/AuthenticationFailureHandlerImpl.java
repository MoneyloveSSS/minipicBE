package com.lizhuopeng.Securityhandler;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.entities.LoginFailCode;
import com.lizhuopeng.utils.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    //post表单中用户名的Key值
    private final String usernameKey="username";

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        LoginFailCode code;

        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            code = LoginFailCode.Auth_FAIL;
        } else if (e instanceof LockedException) {
            code = LoginFailCode.Locked;
        } else if (e instanceof AccountExpiredException) {
            code = LoginFailCode.expired;
        } else if (e instanceof DisabledException) {
            code = LoginFailCode.NonEnabled;
        } else {
            code = LoginFailCode.ERROR;
        }

        String username = httpServletRequest.getParameter(usernameKey);
        log.info("用户[{}]于[{}]登录失败,失败原因：[{}]", username, new Date(), code.getMessage());

        WriteResponse.write(httpServletResponse,new DataResult(code.getCode(),code.getMessage()));
    }
}
