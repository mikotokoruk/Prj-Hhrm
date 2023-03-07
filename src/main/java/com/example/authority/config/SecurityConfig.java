package com.example.authority.config;

import com.example.authority.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @program: hhrm
 * @ClassName SecurityConfig配置类
 * @description:
 * @author: glb
 * @create: 2023-02-01 16:32
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] ETC_URL = {
            "/login",
            "/smartdoc/**",
            "/logout",
            "/captcha",
            "/favicon.ico",
            "/file/**",
            "/aop/**",
            "/user/**",
            "/files/**",

    };
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    protected void configure(HttpSecurity http) throws Exception {
        //登陆配置
        http.cors().and().csrf().disable().formLogin().successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler);
        //禁止session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //拦截规则
        http.authorizeRequests().antMatchers(ETC_URL).permitAll().anyRequest().authenticated();
        //退出规则
        http.logout().logoutSuccessHandler(jwtLogoutSuccessHandler);
        //认证授权
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler);
        http.addFilter(jwtAuthenticationFilter());


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

}
