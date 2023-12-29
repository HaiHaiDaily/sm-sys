package com.sm.controller.admin;

import com.sm.dto.ShoppingInsertDTO;
import com.sm.dto.ShoppingQueryDTO;
import com.sm.entity.Shopping;
import com.sm.result.PageResult;
import com.sm.result.Result;
import com.sm.service.ShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shopping")
@Slf4j
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    /**
     * 新增商品
     * @param shoppingInsertDTO
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody ShoppingInsertDTO shoppingInsertDTO){
        log.info("新增商品：{}",shoppingInsertDTO);
        shoppingService.insert(shoppingInsertDTO);
        return Result.success();
    }

    /**
     * 分页，模糊匹配查询商品
     * @param shoppingQueryDTO
     * @return
     */
    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody ShoppingQueryDTO shoppingQueryDTO){
        log.info("商品分页查询:{}",shoppingQueryDTO);
        PageResult pageResult=shoppingService.pageQuery(shoppingQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id获取商品信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result<Shopping> getInfoById(@PathVariable Long id){
        log.info("根据id获取商品信息,id = {}",id);
        Shopping shopping = shoppingService.getInfoById(id);
        return Result.success(shopping);
    }

    /**
     * 根据id修改商品
     * @param shopping
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Shopping shopping){
        log.info("根据id修改商品:{}",shopping);
        shoppingService.update(shopping);
        return Result.success();
    }

    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        log.info("根据id删除商品,id = {}",id);
        shoppingService.delete(id);
        return Result.success();
    }

}
