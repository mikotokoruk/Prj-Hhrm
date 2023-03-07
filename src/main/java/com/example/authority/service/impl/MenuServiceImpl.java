package com.example.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.entity.Menu;
import com.example.authority.entity.User;
import com.example.authority.mapper.MenuMapper;
import com.example.authority.service.MenuService;
import com.example.authority.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @program: hhrm
 * @ClassName 菜单表 service实现类
 * @description:
 * @author: glb
 * @create: 2023-02-01
 * @Version 2.0
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    MenuMapper menuMapper;
    @Resource
    private UserService userService;

    @Override
    public List<Menu> getmenuTrees(Long id) {
        List<Long> menuIds = menuMapper.getNavMenuIds(id);
        List<Menu> menus = this.listByIds(menuIds);
        menus.sort(Comparator.comparing(Menu::getOrderNum));
        return buildTree(menus);
    }

    @Override
    public List<Menu> getmenuTrees() {
        List<Menu> menus = this.list();
        menus.sort(Comparator.comparing(Menu::getOrderNum));
        return buildTree(menus);
    }

    @Override
    public List<Menu> getRouterByUsername(String username) {
        User user = userService.getByUsername(username);
        List<Long> menuIds = menuMapper.getNavMenuIds(user.getId());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id",menuIds);
        queryWrapper.ne("component", "");
        List<Menu> menus = this.list(queryWrapper);
        return menus;
    }

    public List<Menu> buildTree(List<Menu> menus) {

        List<Menu> finalMenus = new ArrayList<>();
//		通过父类找子类，通过id找
        for (Menu menu : menus) {
            for (Menu e : menus) {

                if (menu.getId() == e.getParentId()) {

                    menu.getChildren().add(e);
                }
            }
            if (menu.getParentId() == 0L) {
                finalMenus.add(menu);
            }
        }
//		提取出父节点


        return finalMenus;
    }
}
