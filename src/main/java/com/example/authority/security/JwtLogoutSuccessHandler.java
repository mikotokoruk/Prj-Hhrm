package com.example.authority.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.example.authority.common.Result;
import com.example.authority.utils.JwtUtils;
import com.example.authority.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: hhrm 退出成功处理类
 * @ClassName JwtLogoutSuccessHandler
 * @description:
 * @author: glb
 * @create: 2023-02-01 16:56
 * @Version 2.0
 **/
@Component
@Slf4j
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    JwtUtils jwtUtils;
    @Resource
    RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (ObjectUtil.isNotEmpty(authentication)) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader(jwtUtils.getHeader(), "");
//        redisUtils.hdel(Constant.MENU_PREFIX,principal.getName());
//        log.warn(principal.getName()+"清除缓存成功！");


        Result result = Result.success("退出成功");

        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }


}
