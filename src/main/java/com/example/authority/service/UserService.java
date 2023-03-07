package com.example.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authority.entity.User;

/**
 * @program: hhrm
 * @ClassName 用户表 service
 * @description:
 * @author: glb
 * @create: 2023-02-01
 * @Version 2.0
 **/
public interface UserService extends IService<User> {

    User getByUsername(String username);

    String getUserAuthorityInfo(Long id);
}
