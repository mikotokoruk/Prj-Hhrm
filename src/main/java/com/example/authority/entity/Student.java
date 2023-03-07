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
 * @ClassName 学生管理 实体类
 * @description:
 * @author: glb
 * @create: 2023-02-07
 * @Version 2.0
 **/
@Getter
@Setter
@TableName("sys_student")
public class Student implements Serializable{

private static final long serialVersionUID=1L;

        /**
         * 序列号
         */
                @TableId(value = "id", type = IdType.AUTO)
                private Long id;

        /**
         * 学生姓名
         */
    @TableField("name")
        private String name;

        /**
         * 个人照片
         */
    @TableField("img")
        private String img;

        /**
         * 个人档案
         */
    @TableField("file")
        private String file;

        /**
         * 出生日期
         */
    @TableField("date")
        private String date;

        /**
         * 个人简介
         */
    @TableField("content")
        private String content;


        }
