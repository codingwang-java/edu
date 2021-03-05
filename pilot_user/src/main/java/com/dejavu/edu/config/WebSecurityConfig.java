package com.dejavu.edu.config;

import com.dejavu.edu.jwt.filter.JWTAuthenticationFilter;
import com.dejavu.edu.jwt.handler.AuthenticationFailHandler;
import com.dejavu.edu.jwt.handler.AuthenticationSuccessHandler;
import com.dejavu.edu.jwt.handler.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author dejavu
 * @description
 * @create 2021-02-23 22:24
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailHandler failHandler;

    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                //由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()

                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin()
                .failureHandler(failHandler).permitAll()
                .successHandler(successHandler).permitAll()
                .and()
                .authorizeRequests()
                //可以匿名访问的链接
                .antMatchers("/user/login").permitAll()

                //其他所有请求需要身份认证
                .anyRequest().authenticated()

                .and()
                //.addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));

    }
}

