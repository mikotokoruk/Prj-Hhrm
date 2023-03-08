package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: hhrm
 * @ClassName 薪金管理 实体类
 * @description:
 * @author: glb
 * @create: 2023-03-08
 * @Version 2.0
 **/
@Getter
@Setter
@TableName("stipend")
public class Stipend implements Serializable{

private static final long serialVersionUID=1L;

        /**
         * 序列号
         */
                @TableId(value = "id", type = IdType.AUTO)
                private Long id;

        /**
         * 姓名
         */
    @TableField("name")
        private String name;

        /**
         * 基本薪资
         */
    @TableField("basic")
        private Integer basic;

        /**
         * 饭补
         */
    @TableField("eat")
        private Integer eat;

        /**
         * 房补
         */
    @TableField("house")
        private Integer house;

        /**
         * 全勤奖
         */
    @TableField("duty")
        private Integer duty;

        /**
         * 赋税
         */
    @TableField("scot")
        private Integer scot;

        /**
         * 额外补助
         */
    @TableField("other")
        private Integer other;


        }
