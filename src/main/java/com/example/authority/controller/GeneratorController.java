package com.example.authority.controller;

import cn.hutool.json.JSONUtil;
import com.example.authority.common.Result;
import com.example.authority.controller.dto.CodeDto;
import com.example.authority.service.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @program: hhrm
 * @ClassName GeneratorController
 * @description:表单创建需要的前端控制器
 * @author: glb
 * @create: 2023-02-04 23:45
 * @Version 1.0
 **/
/**
 * 代码生成器前端控制器
 */
@RestController
@Secured({"ROLE_admin"})
@Slf4j
@RequestMapping("/generator")
public class GeneratorController {
    @Resource
    private GeneratorService generatorService;

    /**
     * 一键生成前后端代码
     *
     * @param codeDto
     * @return
     */
    @PostMapping
    public Result produce(@RequestBody CodeDto codeDto) throws SQLException {

        log.warn(JSONUtil.toJsonStr(codeDto));
        generatorService.produceCode(codeDto);
        return Result.success();
    }

}
