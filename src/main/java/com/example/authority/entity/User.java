package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: hhrm
 * @ClassName 用户表 实体类
 * @description:
 * @author: glb
 * @create: 2023-02-01
 * @Version 2.0
 **/
@Getter
@Setter
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 当前状态
     */
    private Long statu;
    /**
     * 角色Id
     */
    @TableField(exist = false)
    private List<Long> roleIds = new ArrayList<>();
    /**
     * 角色名称
     */
    @TableField(exist = false)
    private List<String> roles = new ArrayList<>();

}
