package com.example.authority.mapper;

import com.example.authority.entity.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 职员管理 Mapper 接口
 * </p>
 *
 * @author glb
 * @since 2023-03-07
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

}
