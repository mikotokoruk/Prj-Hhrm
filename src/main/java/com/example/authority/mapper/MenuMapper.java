package com.example.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authority.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-02-01
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Long> getNavMenuIds(Long id);
}
