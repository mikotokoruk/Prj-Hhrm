package com.example.authority.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authority.common.ClearMenu;
import com.example.authority.common.Constant;
import com.example.authority.common.Result;
import com.example.authority.entity.Menu;
import com.example.authority.entity.User;
import com.example.authority.service.MenuService;
import com.example.authority.service.UserService;
import com.example.authority.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 菜单表 前端控制器
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
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 新增或者修改
     *
     * @param menu
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ClearMenu
    public Result delete(@PathVariable Integer id) {
        return Result.success(menuService.removeById(id));
    }

    /**
     * 查询所有Menu
     *
     * @return
     */
    @GetMapping
    public Result indAll() {
        return Result.success(menuService.list());
    }

    /**
     * 获取单个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
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
        return Result.success(menuService.page(new Page<>(pageNum, pageSize)));
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
        return Result.success(menuService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 获取用户菜单信息
     *
     * @param principal
     * @return
     */
    @GetMapping("/getMenusByUsername")
    public Result getMenusByUsername(Principal principal) {
        String username = principal.getName();
        User user = userService.getByUsername(username);
        List<Menu> menuTrees=new ArrayList<>();
//                = menuService.getmenuTrees(user.getId());

        if(ObjectUtil.isNotEmpty(redisUtils.hget(Constant.MENU_PREFIX,username))){
          menuTrees=(List<Menu>) redisUtils.hget(Constant.MENU_PREFIX,username);
            log.warn("获取了缓存");
        }
        else{
            menuTrees  = menuService.getmenuTrees(user.getId());
            log.warn("没用缓存");
            redisUtils.hset(Constant.MENU_PREFIX,username,menuTrees,Constant.DEFAULT_REDIS_TIME);

        }
        return Result.success(menuTrees);
    }

    /**
     * 获取用户路由信息
     */
    @GetMapping("/getRouters")
    public Result getRouters(Principal principal) {
        List<Menu> routers=menuService.getRouterByUsername(principal.getName());
        return Result.success(routers);
    }

    /**
     * 获取所有菜单信息并且构建树形结构
     *
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        List<Menu> menuTrees = menuService.getmenuTrees();
        return Result.success(menuTrees);
    }
}

