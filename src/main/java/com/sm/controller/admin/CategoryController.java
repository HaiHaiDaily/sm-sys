package com.sm.controller.admin;

import com.sm.dto.CategoryQueryDTO;
import com.sm.entity.Category;
import com.sm.result.PageResult;
import com.sm.result.Result;
import com.sm.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页(模糊匹配)查询
     * @param categoryQueryDTO
     * @return
     */
    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody CategoryQueryDTO categoryQueryDTO){
        log.info("分类分页查询:{}",categoryQueryDTO);
        PageResult pageResult=categoryService.pageQuery(categoryQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id获取分类信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result<Category> infoById(@PathVariable Long id){
        log.info("根据id获取分类信息:{}",id);
        Category category = categoryService.infoById(id);
        return Result.success(category);
    }

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Category category){
        log.info("新增分类：{}",category);
        categoryService.insert(category);
        return Result.success();
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        log.info("根据id删除分类：{}",id);
        categoryService.delete(id);
        return Result.success();
    }

    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        log.info("根据id修改分类信息：{}",category);
        categoryService.update(category);
        return Result.success();
    }

    /**
     * 获取所有已经启用的分类信息
     * @return
     */
    @GetMapping()
    public Result<List<Category>> getAll(){
        log.info("获取所有分类信息");
        List<Category> categoryList = categoryService.getAll();
        return Result.success(categoryList);
    }
}
