package com.example.authority.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authority.common.ClearMenu;
import com.example.authority.common.Result;
import com.example.authority.entity.Role;
import com.example.authority.entity.RoleMenu;
import com.example.authority.service.RoleMenuService;
import com.example.authority.service.RoleService;
import com.example.authority.utils.RedisUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 角色表 前端控制器
 *
 * @program: hhrm
 * @ClassName
 * @description:
 * @author: glb
 * @create: 2023-02-01
 * @Version 2.0
 **/
@RestController
//@PreAuthorize("hasAuthority('role')")/解决普通用户进入页面报错问题
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private RedisUtils redisUtils;


    /**
     * 新增或者修改
     *
     * @param role
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Role role) {
        return Result.success(roleService.saveOrUpdate(role));
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {


        return Result.success(roleService.removeById(id));
    }

    /**
     * 查询所有Role
     *
     * @return
     */
    @GetMapping
    public Result indAll() {
        return Result.success(roleService.list());
    }

    /**
     * 获取单个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.getById(id));
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
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.like("name", name);
        return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete/{ids}")
    @Transactional
    @ClearMenu
    public Result deleteByIds(@PathVariable String[] ids) {
        return Result.success(roleService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 获取该角色下的menu菜单id，返回到前端，添加到树形组件里面
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", id);
        List<RoleMenu> rolemenus = roleMenuService.list(queryWrapper);
        Role role = roleService.getById(id);
        role.setMenusId(rolemenus.stream().map(roleMenu -> roleMenu.getMenuId()).collect(Collectors.toList()));
        return Result.success(role);
    }

    /**
     * 重新给该名角色分配菜单权限
     *
     * @param role
     * @return
     */
    @PostMapping("/perm")
    @ClearMenu
    public Result perm(@RequestBody Role role) {
        //先删除之前的role拥有的所有菜单
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", role.getId());
        List<RoleMenu> rolemenus = roleMenuService.list(queryWrapper);

        roleMenuService.removeByIds(rolemenus.stream().map(roleMenu -> roleMenu.getId()).collect(Collectors.toList()));
        //再次添加现在所拥有的角色
        role.getMenusId().forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(menuId);
            roleMenuService.save(roleMenu);

        });
//        redisUtils.del(Constant.MENU_PREFIX);
        return Result.success();
    }


}

