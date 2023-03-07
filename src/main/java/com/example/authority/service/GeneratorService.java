package com.example.authority.service;

import com.example.authority.controller.dto.CodeDto;

import java.sql.SQLException;

/**
 * @program: hhrm
 * @interfaceName GeneratorService
 * @description:
 * @author: 哔哩哔哩 菜鸟程序员小胖 微信navicat15
 * @create: 2023-02-05 14:22
 * @Version 2.0
 **/
public interface GeneratorService {
    void produceCode(CodeDto codeDto) throws SQLException;
}
