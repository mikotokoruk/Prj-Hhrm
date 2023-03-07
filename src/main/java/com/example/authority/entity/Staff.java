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
 * @ClassName 职员管理 实体类
 * @description:
 * @author: glb
 * @create: 2023-03-07
 * @Version 2.0
 **/
@Getter
@Setter
@TableName("staff")
public class Staff implements Serializable{

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
         * 性别
         */
    @TableField("gender")
        private String gender;

        /**
         * 年龄
         */
    @TableField("age")
        private Integer age;

        /**
         * 生日
         */
    @TableField("birth")
        private String birth;

        /**
         * 联系方式
         */
    @TableField("phone")
        private String phone;

        /**
         * 部门
         */
    @TableField("department")
        private String department;

        /**
         * 职位
         */
    @TableField("post")
        private String post;

        /**
         * 照片
         */
    @TableField("photo")
        private String photo;

        /**
         * 简历
         */
    @TableField("resume")
        private String resume;


        }
