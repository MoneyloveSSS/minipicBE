package com.lizhuopeng.Securityhandler;

import com.lizhuopeng.entities.DataResult;
import com.lizhuopeng.entities.RespCode;
import com.lizhuopeng.utils.WriteResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录访问受保护资源时，返回json
 */
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        WriteResponse.write(httpServletResponse, new DataResult().setCode(RespCode.UNAUTHORIZED.getCode()).setMessage(RespCode.UNAUTHORIZED.getMessage()));
    }
}
