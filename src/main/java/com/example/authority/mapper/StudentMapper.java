package com.example.authority.mapper;

import com.example.authority.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生管理 Mapper 接口
 * </p>
 *
 * @author bilibili 菜鸟程序员小胖 
 * @since 2023-02-07
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
