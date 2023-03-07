package com.example.authority.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.json.JSONUtil;
import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.example.authority.common.Constant;
import com.example.authority.controller.dto.CodeDto;
import com.example.authority.controller.dto.DomanDto;
import com.example.authority.entity.Menu;
import com.example.authority.service.GeneratorService;
import com.example.authority.service.MenuService;
import com.example.authority.utils.RedisUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @program: hhrm
 * @ClassName GeneratorServiceImpl
 * @description:
 * @author: glb
 * @create: 2023-02-05 14:22
 * @Version 2.0
 **/
@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value(("${spring.datasource.password}"))
    private String password;
    @Value("${generator.author}")
    private String author;
    @Value("${spring.datasource.driver-class-name}")
    private String datasource;
    @Resource
    private MenuService menuService;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 代码生成器
     *
     * @param codeDto
     * @throws SQLException
     */
    @Override
    public void produceCode(CodeDto codeDto) {
        try {
            this.produeTable(codeDto);
            this.produceSqlDocument();
            this.produceBackendCode(codeDto);
            this.produceVue(codeDto);
            redisUtils.del(Constant.MENU_PREFIX);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }


    /**
     * 创建数据库表！此处应该注意sql注入的问题
     *
     * @param codeDto
     */
    public void produeTable(CodeDto codeDto) throws SQLException {
        log.info("===================");
        log.info("开始创建数据库表和menu菜单数据");
        DataSource ds = getDatasource();
        String sql = "DROP TABLE IF EXISTS `" + codeDto.getTable() + "`;";
        //执行删除已经存在的表的操作
        Db.use(ds).execute(sql);
        //执行创建表的sql语句
        log.info("开始创建数据库表");

        Db.use(ds).execute(codeDto.getSql());
        log.info("创建数据库表成功");
        //        去掉前缀 自己可以在Constant.java中自己配置前缀常量！

        String table = codeDto.getTable();
        table = StrUtil.removePrefix(table, Constant.PREFIX1);
        //创建menu菜单数据
        Menu menu = new Menu();
        menu.setTitle(codeDto.getRemark());
        menu.setParentId(0L);
        menu.setPath("/"+table);
        menu.setComponent(StrUtil.upperFirst(table));
        menu.setIcon(Constant.DEFAULT_ICON);
        menu.setPerms(table);
        menu.setOrderNum(999);
        menu.setStatu(0L);
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        //删除掉已经存在的menu
        menuQueryWrapper.eq("path", menu.getPath());
        menuService.remove(menuQueryWrapper);
        //保存新的menu
        log.info("创建menu表数据成功");
        menuService.save(menu);
        log.info("===================");


    }

    /**
     * 根据mybatis-plus来生成后端代码
     * velocity模版
     *
     * @param codeDto
     */
    public void produceBackendCode(CodeDto codeDto) {

        log.info("===================");
        log.info("开始创建后端代码");
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.authority") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .entity("entity")

                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(codeDto.getTable()) // 设置需要生成的表名
                            .addTablePrefix(Constant.PREFIX1, Constant.PREFIX2)

                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                    // 设置过滤表前缀 可以在Constant自行设置
                })
                // 使用Velocity引擎模板
                .execute();
        log.info("后端代码创建成功");
        log.info("===================");


    }

    /**
     * 获取数据源
     *
     * @return
     */
    private DataSource getDatasource() {
        return new SimpleDataSource(url, username, password);
    }

    /**
     * 生成数据库文档
     */
    public void produceSqlDocument() {

        log.info("===================");
        log.info("开始生成数据库设计文档");
        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(datasource);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(System.getProperty("user.dir") + "/src/main/resources/static/")
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.WORD)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .build();

        //忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();


        ProcessConfig processConfig = ProcessConfig.builder()
              .build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("2.0.0")
                //描述
                .description("数据库设计文档")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig)
                .build();
        //执行生成
        new DocumentationExecute(config).execute();
        log.info("数据库设计文档生成成功");
        log.info("===================");

    }

    /**
     * 创建vue页面
     *
     * @param codeDto
     */
    private void produceVue(CodeDto codeDto) throws IOException {
        log.info("===================");
        log.info("开始创建vue页面");
        log.warn(JSONUtil.toJsonStr(codeDto.getDomanins()));
        //初始domanDtos
        List<DomanDto> domanDtos = codeDto.getDomanins();
        //将所有带有下划线的字段转译成驼峰字段
        domanDtos.forEach(domanDto -> {
            domanDto.setName(StrUtil.toCamelCase(domanDto.getName()));
        });
        codeDto.setDomanins(domanDtos);
        //        设置velocity的资源加载器
        Properties properties = new Properties();
        properties.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //        初始化velocity引擎
        Velocity.init(properties);
        //        创建velocity容器
        VelocityContext context = new VelocityContext();
        // 设置表单数据
        context.put("tableData", codeDto.getDomanins());
        //设置请求数据
        context.put("tableName", StrUtil.removePrefix(codeDto.getTable() ,Constant.PREFIX1));
        //设置组件名称
        context.put("components",StrUtil.upperFirst(StrUtil.removePrefix(codeDto.getTable() ,Constant.PREFIX1)));

        //        加载velocity模版文件
        Template tpl = Velocity.getTemplate("templates/table.vue.vm", "UTF-8");
        //        设置输出路径
        log.info(System.getProperty("user.dir") );
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/authority-ui/src/views/" + StrUtil.upperFirst(StrUtil.removePrefix(codeDto.getTable(), Constant.PREFIX1)) + ".vue");
        log.info("前端输出路径：" + System.getProperty("user.dir") + "/authority-ui/views/" + StrUtil.upperFirst(codeDto.getTable()) + ".vue");
        //合并数据到模板
        tpl.merge(context, fw);

        //释放资源
        fw.close();
        log.info("前端vue生成成功");
        log.info("===================");

    }

}
