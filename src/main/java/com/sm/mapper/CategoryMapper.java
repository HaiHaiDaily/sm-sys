package com.sm.mapper;

import com.github.pagehelper.Page;
import com.sm.dto.CategoryQueryDTO;
import com.sm.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 动态模糊匹配，分页查询
     * @param categoryQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryQueryDTO categoryQueryDTO);

    /**
     * 根据id查询分类信息
     * @param id
     * @return
     */
    @Select("select id, name, sort, status, description, create_time, update_time, create_user, update_user, employee_name from category where id = #{id}")
    Category GetById(Long id);

    /**
     * 根据分类名称查询分类信息
     * @param name
     * @return
     */
    @Select("select id, name, sort, status, description, create_time, update_time, create_user, update_user, employee_name from category where name = #{name}")
    Category getByName(String name);

    /**
     * 新增分类信息
     * @param category
     */
    @Insert("insert into category( name, description,sort ,create_time, update_time, create_user, update_user, employee_name) " +
            "values (#{name},#{description},#{sort},#{createTime},#{updateTime},#{createUser},#{updateUser},#{employeeName})")
    void insert(Category category);

    /**
     * 根据id删除分类信息
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * 动态修改分类信息
     * @param category
     */
    void update(Category category);

    /**
     * 获取所有分类信息
     * @return
     */
    @Select("select id, name, sort, status, description, create_time, update_time, create_user, update_user, employee_name from category where status = 1")
    List<Category> getAll();

    /**
     * 根据id查询分类信息
     * @param categoryId
     * @return
     */
    @Select("select id, name, sort, status, description, create_time, update_time, create_user, update_user, employee_name from category where id = #{categoryId}")
    Category getById(Long categoryId);

}
