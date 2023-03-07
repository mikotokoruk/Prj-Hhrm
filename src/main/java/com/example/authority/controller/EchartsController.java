package com.example.authority.controller;

import cn.hutool.json.JSONUtil;
import com.example.authority.common.Result;
import com.example.authority.service.FileService;
import com.example.authority.service.MenuService;
import com.example.authority.service.RoleService;
import com.example.authority.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: hhrm
 * @ClassName EchartsController
 * @description:
 * @author: glb
 * @create: 2023-02-06 20:41
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private UserService userService;
    @Resource
    private MenuService menuService;
    @Resource
    private RoleService roleService;
    @Resource
    private FileService fileService;


    /**
     * 获取系统数据并且显示出来
     *
     * @return
     */
    @GetMapping("/systemparameter")
    public Result systemparameter() {
        Map<String, List> map = new HashMap<>();
        String[] names={"用户总数", "菜单总数", "角色总数","文件总数"};
        List<String>  ydata= Arrays.asList(names);
        List<Integer> xdata=new ArrayList<>();
//        获取用户总数
        xdata.add(userService.list().size());
//        获取菜单总数
        xdata.add(menuService.list().size());
//        获取角色总数
        xdata.add(roleService.list().size());
        xdata.add(fileService.list().size());


//        添加到map中
        map.put("series",xdata);
        map.put("yAxis",ydata);

        return Result.success(map);
    }

}
