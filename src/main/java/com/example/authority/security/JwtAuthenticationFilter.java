package com.example.authority.security;

/**
 * @program: hhrm
 * @ClassName JwtAuthenticationFilter
 * @description:
 * @author: glb
 * @create: 2023-02-01 17:12
 * @Version 1.0
 **/


import cn.hutool.core.util.StrUtil;
import com.example.authority.common.Constant;
import com.example.authority.entity.User;
import com.example.authority.service.UserService;
import com.example.authority.service.impl.UserDetailServiceImpl;
import com.example.authority.utils.JwtUtils;
import com.example.authority.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据jwt自动登陆
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Resource
    RedisUtils redisUtils;

    @Resource
    UserDetailServiceImpl userDetailService;

    @Autowired
    UserService sysUserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //退出清除缓存
        String jwt = request.getHeader(jwtUtils.getHeader());
//        String url = request.getRequestURI();
//        System.out.println(url+"当前的url");
//        if ("/logout".equals(url) && request.getMethod().equals("POST")) {
//            Claims claims=jwtUtils.getClaimByToken(jwt);
//            log.info("清除缓存开始");
//            redisUtils.hdel(Constant.MENU_PREFIX,claims.getSubject());
//            log.info(claims.getSubject()+"清除成功！");
//
//        }



//      jwt为空就继续执行其他的过滤器链 为空和为未定义即需要到下一个过滤器链
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }

        Claims claim = jwtUtils.getClaimByToken(jwt);
        if (claim == null) {
            throw new JwtException("token 异常");
        }
        if (jwtUtils.isTokenExpired(claim)) {
            throw new JwtException("token已过期");
        }

        String username = claim.getSubject();
        // 获取用户的权限等信息

        User user = sysUserService.getByUsername(username);

        //自动登陆
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, null, userDetailService.getUserAuthority(user.getId()));
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
}
