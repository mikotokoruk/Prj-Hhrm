//package com.example.authority.utils;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.example.authority.common.Constant;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.Collections;
//
///**
// * @program: hhrm
// * @ClassName Genreatorutils
// * @description:
// * @author: glb
// * @create: 2023-02-08 19:53
// * @Version 1.0
// **/
//@Slf4j
//public class Genreatorutils {
//
//    public static void main(String[] args) {
//        log.info("===================");
//        log.info("开始创建后端代码");
//        FastAutoGenerator.create(url, username, password)
//                .globalConfig(builder -> {
//                    builder.author(author) // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(System.getProperty("user.dir") + "/src/main/java/"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.example.authority") // 设置父包名
//                            .moduleName(null) // 设置父包模块名
//                            .entity("entity")
//
//                            .service("service")
//                            .serviceImpl("service.impl")
//                            .mapper("mapper")
//                            .xml("mapper.xml")
//                            .controller("controller")
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude(codeDto.getTable()) // 设置需要生成的表名
//                            .addTablePrefix(Constant.PREFIX1, Constant.PREFIX2)
//
//                            .serviceBuilder()
//                            .formatServiceFileName("%sService")
//                            .formatServiceImplFileName("%sServiceImpl")
//                            .entityBuilder()
//                            .enableLombok()
//                            .logicDeleteColumnName("deleted")
//                            .enableTableFieldAnnotation()
//                            .controllerBuilder()
//                            .enableRestStyle()
//                            .formatFileName("%sController")
//                            .mapperBuilder()
//                            .enableBaseResultMap()  //生成通用的resultMap
//                            .superClass(BaseMapper.class)
//                            .formatMapperFileName("%sMapper")
//                            .enableMapperAnnotation()
//                            .formatXmlFileName("%sMapper");
//                    // 设置过滤表前缀 可以在Constant自行设置
//                })
//                // 使用Velocity引擎模板
//                .execute();
//        log.info("后端代码创建成功");
//        log.info("===================");
//
//
//
//    }
//
//}
