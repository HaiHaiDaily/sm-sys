package com.sm.service;

import com.sm.dto.CategoryQueryDTO;
import com.sm.entity.Category;
import com.sm.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 分页查询
     * @param categoryQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryQueryDTO categoryQueryDTO);

    /**
     * 获取分类信息
     * @param id
     * @return
     */
    Category infoById(Long id);

    /**
     * 新增分类
     * @param category
     */
    void insert(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    void delete(Long id);

    /**
     * 修改分类信息
     * @param category
     */
    void update(Category category);

    /**
     * 获取所有分类信息
     * @return
     */
    List<Category> getAll();

}
