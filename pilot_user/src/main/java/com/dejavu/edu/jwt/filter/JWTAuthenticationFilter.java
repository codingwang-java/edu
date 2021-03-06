package com.dejavu.edu.jwt.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.dejavu.edu.constant.SecurityConstant;
import com.dejavu.edu.utils.ResponseUtil;
import com.dejavu.edu.utils.ResultUtils;
import com.google.common.reflect.TypeToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dejavu
 * @description
 * @create 2021-02-24 23:49
 **/
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(SecurityConstant.HEADER);
        if(StringUtils.isBlank(header)){
            header = request.getParameter(SecurityConstant.HEADER);
        }
        Boolean notValid = StringUtils.isBlank(header) || (!header.startsWith(SecurityConstant.TOKEN_SPLIT));
        if (notValid) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            e.toString();
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header, HttpServletResponse response) {

        // ?????????
        String username = null;
        // ??????
        List<GrantedAuthority> authorities = new ArrayList<>();

        // JWT
        try {
            // ??????token
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                    .parseClaimsJws(header.replace(SecurityConstant.TOKEN_SPLIT, ""))
                    .getBody();

            // ???????????????
            username = claims.getSubject();
            String authority = claims.get(SecurityConstant.AUTHORITIES).toString();
            if(StringUtils.isNotBlank(authority)){
                List<String> list = (List<String>) JSONObject.toJSON(authority, (ParserConfig) new TypeToken<List<String>>(){}.getType());
                for(String ga : list){
                    authorities.add(new SimpleGrantedAuthority(ga));
                }
            }
        } catch (ExpiredJwtException e) {
            ResponseUtil.out(response, ResultUtils.failaureResult(401,"?????????????????????????????????"));
        } catch (Exception e){
            log.error(e.toString());
            ResponseUtil.out(response, ResultUtils.failaureResult(500,"??????token??????"));
        }

        if(StringUtils.isNotBlank(username)) {
            // ???????????? ??????password?????????null
            User principal = new User(username, "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
        return null;
    }
}
