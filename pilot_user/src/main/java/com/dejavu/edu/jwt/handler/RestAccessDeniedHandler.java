package com.dejavu.edu.jwt.handler;

import com.dejavu.edu.utils.ResponseUtil;
import com.dejavu.edu.utils.ResultUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dejavu
 * @description
 * @create 2021-02-24 23:53
 **/
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        ResponseUtil.out(response, ResultUtils.failaureResult(403,"抱歉，您没有访问权限"));
    }
}
