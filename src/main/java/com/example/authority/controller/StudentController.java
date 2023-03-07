package com.example.authority.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.example.authority.common.Result;

import java.util.Arrays;

import org.springframework.transaction.annotation.Transactional;

import com.example.authority.service.StudentService;
import com.example.authority.entity.Student;




/**
 * 学生管理 前端控制器
 */
@RestController
@RequestMapping("/student")
    public class StudentController {

@Resource
private StudentService studentService;
/**
 * 新增或者修改
 * @param student
 * @return
 */
@PostMapping
public Result save(@RequestBody Student student){
        return Result.success(studentService.saveOrUpdate(student));
        }
/**
  * 单个删除
  * @param id
  * @return
  */
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
        return Result.success(studentService.removeById(id));
        }
/**
 * 查询所有Student
 * @return
 */
@GetMapping
public Result indAll(){
        return Result.success(studentService.list());
        }
/**
  * 获取单个
  * @param id
  * @return
  */
@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id){
        return Result.success(studentService.getById(id));
        }
/**
 * 分页显示
 * @param name
 * @param pageNum
 * @param pageSize
 * @return
 */
@GetMapping("/page")
public Result findPage(
@RequestParam(defaultValue = "") String name,
@RequestParam(defaultValue = "1") Integer pageNum,
@RequestParam(defaultValue = "10") Integer pageSize){
        QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
        if(!"".equals(name)){
        queryWrapper.like("name",name);

        }
        queryWrapper.orderByDesc("id");
        return Result.success(studentService.page(new Page<>(pageNum,pageSize),queryWrapper));
        }
/**
* 批量删除
* @param ids
* @return
*/
@PostMapping("/delete/{ids}")
@Transactional
public Result deleteByIds(@PathVariable String[]ids){
        return Result.success(studentService.removeByIds(Arrays.asList(ids)));
        }
        }

