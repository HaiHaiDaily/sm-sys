package com.sm.service;

import com.sm.dto.ShoppingInsertDTO;
import com.sm.dto.ShoppingQueryDTO;
import com.sm.entity.Shopping;
import com.sm.result.PageResult;

public interface ShoppingService {

    /**
     * 新增商品
     * @param shoppingInsertDTO
     */
    void insert(ShoppingInsertDTO shoppingInsertDTO);

    /**
     * 商品分页查询
     * @param shoppingQueryDTO
     * @return
     */
    PageResult pageQuery(ShoppingQueryDTO shoppingQueryDTO);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Shopping getInfoById(Long id);

    /**
     * 根据id删除商品信息
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id修改商品信息
     * @param shopping
     */
    void update(Shopping shopping);
}
