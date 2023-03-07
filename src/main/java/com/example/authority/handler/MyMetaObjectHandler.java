package com.example.authority.handler;

/**
 * @program: hhrm
 * @ClassName MyMetaObjectHandler 自动填充
 * @description:
 * @author: glb
 * @create: 2023-02-05 16:42
 * @Version 1.0
 **/

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时候的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill");
        //添加时候自动填充 setFieldValByName三个参数为：映射类字段，填充值，原对象
        // setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        //给createTime这个字段和updateTime这俩字段 来一个什么值呢 来一个自动插入时间 传一个数据 这个数据就是mataObject
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);

    }

    /**
     * 更新时候的填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill");
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);

    }
}
