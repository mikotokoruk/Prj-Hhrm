package com.example.authority.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.example.authority.common.Result;

import java.util.Arrays;

import org.springframework.transaction.annotation.Transactional;

import com.example.authority.service.StipendService;
import com.example.authority.entity.Stipend;




/**
 * 薪金管理 前端控制器
 */
@RestController
@RequestMapping("/stipend")
    public class StipendController {

@Resource
private StipendService stipendService;
/**
 * 新增或者修改
 * @param stipend
 * @return
 */
@PostMapping
public Result save(@RequestBody Stipend stipend){
        return Result.success(stipendService.saveOrUpdate(stipend));
        }
/**
  * 单个删除
  * @param id
  * @return
  */
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
        return Result.success(stipendService.removeById(id));
        }
/**
 * 查询所有Stipend
 * @return
 */
@GetMapping
public Result indAll(){
        return Result.success(stipendService.list());
        }
/**
  * 获取单个
  * @param id
  * @return
  */
@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id){
        return Result.success(stipendService.getById(id));
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
        QueryWrapper<Stipend>queryWrapper=new QueryWrapper<>();
        if(!"".equals(name)){
        queryWrapper.like("name",name);

        }
        queryWrapper.orderByDesc("id");
        return Result.success(stipendService.page(new Page<>(pageNum,pageSize),queryWrapper));
        }
/**
* 批量删除
* @param ids
* @return
*/
@PostMapping("/delete/{ids}")
@Transactional
public Result deleteByIds(@PathVariable String[]ids){
        return Result.success(stipendService.removeByIds(Arrays.asList(ids)));
        }
        }

