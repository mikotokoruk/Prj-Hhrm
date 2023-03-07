package com.example.authority.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authority.common.Constant;
import com.example.authority.common.Result;
import com.example.authority.controller.dto.RepasswordDto;
import com.example.authority.entity.Role;
import com.example.authority.entity.User;
import com.example.authority.entity.UserRole;
import com.example.authority.service.RoleService;
import com.example.authority.service.UserRoleService;
import com.example.authority.service.UserService;
import com.example.authority.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户表 前端控制器
 *
 * @program: hhrm
 * @ClassName
 * @description:
 * @author: glb
 * @create: 2023-02-01
 * @Version 2.0
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public Result save(@RequestBody User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (user.getId() == null) {
            queryWrapper.eq("username", user.getUsername());
            List<User> list = userService.list(queryWrapper);
            //验重
            if (list.size() > 0) {
                return Result.error("当前用户名已经存在，请更换！");
            }
            //设置默认头像
            if (user.getAvatar() == null) {
                user.setAvatar(Constant.DEFAULT_AVATAR);
            }
            /**
             * 修复密码不加密的问题
             */
            user.setPassword(passwordEncoder.encode(Constant.DEFAULT_PASSWORD));
            userService.saveOrUpdate(user);
            User InitUser = userService.getByUsername(user.getUsername());
            UserRole userRole = new UserRole();
            userRole.setUserId(InitUser.getId());
            //设置默认角色
            userRole.setRoleId(Constant.DEFAULT_ROLE);
            userRoleService.save(userRole);

        }

        userService.saveOrUpdate(user);
        return Result.success();
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        /**
         * 删除掉userRole里面的数据
         */
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);

        userRoleService.remove(queryWrapper);
        /**
         * 删除掉user的菜单缓存
         */
        User user = userService.getById(id);
        redisUtils.hdel(Constant.MENU_PREFIX, user.getUsername());
        return Result.success(userService.removeById(id));
    }

    /**
     * 查询所有User
     *
     * @return
     */
    @GetMapping
    public Result indAll() {
        return Result.success(userService.list());
    }

    /**
     * 获取单个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    /**
     * 分页显示
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", name);
        queryWrapper.orderByDesc("id");
        Page<User> page = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<User> userList = page.getRecords();

        userList.forEach(user -> {
            //先获取用户的角色id
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();

            userRoleQueryWrapper.eq("user_id", user.getId());
            List<UserRole> roleRoles = userRoleService.list(userRoleQueryWrapper);
            if (roleRoles.size() > 0) {

                List<Role> roles = roleService.listByIds(roleRoles.stream().map(r -> r.getRoleId()).collect(Collectors.toList()));
                roles.forEach(role -> {
                    user.getRoles().add(role.getName());
                });
            }

        });
        page.setRecords(userList);
        return Result.success(page);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete/{ids}")
    @Transactional
    public Result deleteByIds(@PathVariable String[] ids) {
        return Result.success(userService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 获取该名用户的角色
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable Long id) {
        User user = userService.getById(id);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        List<UserRole> userRoles = userRoleService.list(queryWrapper);
        user.setRoleIds(userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList()));
        return Result.success(user);

    }

    /**
     * 获取用户信息
     *
     * @param principal
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(Principal principal) {
        log.info(principal.getName());
        return Result.success(userService.getByUsername(principal.getName()));
    }

    /**
     * 修改密码
     *
     * @param repasswordDto
     * @param principal
     * @return
     */
    @PostMapping("/repassword")
    public Result repassword(@RequestBody RepasswordDto repasswordDto, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        //先比对原来的密码是否正确
        if (!passwordEncoder.matches(repasswordDto.getOldpass(), user.getPassword())) {
            return Result.error("旧密码输入错误！");
        } else {
            user.setPassword(passwordEncoder.encode(repasswordDto.getNewpass()));
            userService.saveOrUpdate(user);
            return Result.success();
        }


    }

    /**
     * 清除缓存
     *
     * @param principal
     * @return
     */
    @PostMapping("/clear")
    public Result getUserInfo1(Principal principal) {
        log.info(principal.getName() + "清除当前用户菜单和权限缓存");
        redisUtils.hdel(Constant.MENU_PREFIX, principal.getName());
        redisUtils.hdel(Constant.AUTHORITY, principal.getName());
        log.info("清除" + principal.getName() + "菜单缓存缓存成功！");
        return Result.success(userService.getByUsername(principal.getName()));
    }


    /**
     * 注册账户
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        User user1 = userService.getByUsername(user.getUsername());
        if (ObjectUtil.isNotEmpty(user1)) {
            return Result.error("当前用户名已经存在，请您更换！");
        } else {

            user.setAvatar(Constant.DEFAULT_AVATAR);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveOrUpdate(user);
            user = userService.getByUsername(user.getUsername());
            UserRole userRole = new UserRole();
            userRole.setRoleId(Constant.DEFAULT_ROLE);
            userRole.setUserId(user.getId());
            userRoleService.save(userRole);
            return Result.success();
        }

    }

    /**
     * 处理分配角色权限失败的问题
     *
     * @param user
     * @return
     */
    @PostMapping("/perm")
    public Result perm(@RequestBody User user) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        userRoleService.remove(queryWrapper);
        for (Long i : user.getRoleIds()) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(i);
            userRole.setUserId(user.getId());
            userRoleService.saveOrUpdate(userRole);
        }
        return Result.success();
    }


}

