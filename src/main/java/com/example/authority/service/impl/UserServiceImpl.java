package com.example.authority.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.common.Constant;
import com.example.authority.entity.Menu;
import com.example.authority.entity.Role;
import com.example.authority.entity.User;
import com.example.authority.mapper.MenuMapper;
import com.example.authority.mapper.UserMapper;
import com.example.authority.service.MenuService;
import com.example.authority.service.RoleService;
import com.example.authority.service.UserRoleService;
import com.example.authority.service.UserService;
import com.example.authority.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: hhrm
 * @ClassName 用户表 service实现类
 * @description:
 * @author: glb
 * @create: 2023-02-01
 * @Version 2.0
 **/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;
    @Resource
    RedisUtils redisUtils;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuService menuService;

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        return this.getOne(queryWrapper);
    }

    @Override
    public String getUserAuthorityInfo(Long id) {
        String authority = "";
        //获取角色
        //先判断是否存在缓存，如果不存在则获取

        User user = this.getById(id);
        if (ObjectUtil.isNotEmpty(redisUtils.hget(Constant.AUTHORITY, user.getUsername()))) {
            authority = (String) redisUtils.hget(Constant.AUTHORITY, user.getUsername());
            log.info(authority+"获取当前用户的权限缓存");
        } else {
            QueryWrapper userRoleWrapper = new QueryWrapper();
            userRoleWrapper.inSql("id", "select role_id from sys_user_role where user_id= " + id);
            List<Role> roles = roleService.list(userRoleWrapper);
            if (roles.size() > 0) {
                String rolecodes = roles.stream().map(m -> "ROLE_" + m.getPerms()).collect(Collectors.joining(","));
                authority = rolecodes.concat(",");
            }

            //获取权限
            List<Long> menuIds = menuMapper.getNavMenuIds(id);
            if (menuIds.size() > 0) {
                List<Menu> sysMenus = menuService.listByIds(menuIds);
                String menusPerms = sysMenus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));
                authority = authority.concat(menusPerms);

            }

            redisUtils.hset(Constant.AUTHORITY, user.getUsername(), authority);
        }

        log.info(JSONUtil.toJsonStr(authority));
        return authority;
    }
}
