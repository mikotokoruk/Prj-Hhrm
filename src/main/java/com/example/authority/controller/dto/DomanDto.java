package com.example.authority.controller.dto;

import lombok.Data;

/**
 * @program: hhrm
 * @ClassName DomanDto
 * @description:
 * @author: glb
 * @create: 2023-02-05 13:30
 * @Version 1.0
 **/
@Data
public class DomanDto {
    private String name;//字段名称
    private String datatype;//数据类型长度
    private String formtype;//表单类型
    private String ismust;//是否为必选项
    private String comment;//字段注释，表单名称


}
