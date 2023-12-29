package com.sm.mapper;

import com.github.pagehelper.Page;
import com.sm.dto.ShoppingInsertDTO;
import com.sm.dto.ShoppingQueryDTO;
import com.sm.entity.Employee;
import com.sm.entity.Shopping;
import com.sm.vo.ShoppingQueryVO;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface ShoppingMapper {

    /**
     * 新增商品
     * @param shopping
     */
    @Insert("insert into shopping(name, category_id, price, image, description, status, create_time, update_time, create_user, update_user, number, employee_name)" +
            "values (#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser},#{number},#{employeeName})")
    void insert(Shopping shopping);

    /**
     * 根据商品名称查找商品
     * @param name
     * @return
     */
    @Select("select id, name, category_id, price, image, description, status, create_time, update_time, create_user, update_user, number, employee_name from shopping where name = #{name}")
    Shopping getByName(String name);

    /**
     * 分页，动态模糊匹配查询
     * @param shoppingQueryDTO
     * @return
     */
    Page<ShoppingQueryVO> pageQuery(ShoppingQueryDTO shoppingQueryDTO);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @Select("select id, name, category_id, price, image, description, status, create_time, update_time, create_user, update_user, number, employee_name from shopping where id = #{id}")
    Shopping getById(Long id);

    /**
     * 根据id删除商品
     * @param id
     */
    @Delete("delete from shopping where id = #{id}")
    void delete(Long id);

    /**
     * 动态修改商品信息
     * @param shopping
     */
    void update(Shopping shopping);

    /**
     * 根据分类categoryId修改商品状态
     * @param categoryId
     */
    @Update("update shopping set status = 0 where category_id = #{categoryId}")
    void updateByCategoryId(Long categoryId);

    /**
     * 根据动态条件统计商品数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
