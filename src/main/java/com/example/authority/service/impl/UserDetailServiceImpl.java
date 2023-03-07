package com.example.authority.service.impl;

import com.example.authority.entity.User;
import com.example.authority.security.Account;
import com.example.authority.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @program: hhrm
 * @ClassName UserDetailServiceImpl
 * @description:
 * @author: glb
 * @create: 2023-02-01 17:15
 * @Version 2.0
 **/
@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或者密码不正确");
        }

        if (user.getStatu() == 1L) {

            throw new LockedException("账户被锁定,请联系管理员");
        }
        return new Account(user.getId(), user.getUsername(), user.getPassword(), null);
    }

    public Collection<? extends GrantedAuthority> getUserAuthority(Long id) {
        String authority = userService.getUserAuthorityInfo(id);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
