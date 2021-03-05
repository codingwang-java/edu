package com.dejavu.edu.service.seviceImpl;

import com.dejavu.edu.service.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dejavu
 * @description
 * @create 2021-02-28 16:45
 **/
@Service
public class UserServiceImpl implements UserDetailsService , UserService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(!"admin".equals(s)){
            throw new UsernameNotFoundException("用户不存在");
        }
        String password = passwordEncoder.encode("123");
        return new User(s,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc,/main.html"));
    }
}
