package com.dejavu.edu.jwt.handler;

import com.dejavu.edu.utils.ResponseUtil;
import com.dejavu.edu.utils.ResultUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dejavu
 * @description
 * @create 2021-02-24 23:52
 **/
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {


        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            ResponseUtil.out(response, ResultUtils.failaureResult(500,"用户名或密码错误"));
        } else if (e instanceof DisabledException) {
            ResponseUtil.out(response, ResultUtils.failaureResult(500,"账户被禁用，请联系管理员"));
        } else {
            ResponseUtil.out(response, ResultUtils.failaureResult(500,"登录失败，其他内部错误"));
        }
    }
}
