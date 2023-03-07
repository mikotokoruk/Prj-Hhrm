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
 * @ClassName 角色表 实体类
 * @description:
 * @author: glb
 * @create: 2023-02-01
 * @Version 2.0
 **/
@Getter
@Setter
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单id
     */
    @TableField(exist = false)
    List<Long> menusId = new ArrayList<>();
    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 唯一标识
     */
    private String perms;
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
    private Integer statu;

}
