package com.sm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sm.constant.MessageConstant;
import com.sm.constant.PasswordConstant;
import com.sm.constant.StatusConstant;
import com.sm.constant.TypeConstant;
import com.sm.context.BaseContext;
import com.sm.dto.ShoppingInsertDTO;
import com.sm.dto.ShoppingQueryDTO;
import com.sm.entity.Category;
import com.sm.entity.Employee;
import com.sm.entity.Shopping;
import com.sm.exception.BaseException;
import com.sm.mapper.CategoryMapper;
import com.sm.mapper.EmployeeMapper;
import com.sm.mapper.ShoppingMapper;
import com.sm.result.PageResult;
import com.sm.service.ShoppingService;
import com.sm.vo.ShoppingQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增商品
     * @param shoppingInsertDTO
     */
    public void insert(ShoppingInsertDTO shoppingInsertDTO) {

        //根据商品名称查找商品
        Shopping shopping = shoppingMapper.getByName(shoppingInsertDTO.getName());
        if(shopping!=null){
            //该商品已存在
            throw new BaseException(MessageConstant.SHOPPING_EXISTS);
        }

        //创建一个员工实体类
        shopping=new Shopping();

        //对象属性拷贝 前对象属性copy到后一个对象属性
        BeanUtils.copyProperties(shoppingInsertDTO,shopping);

        //设置账号的状态, 默认正常状态1表示正常0表示锁定
        shopping.setStatus(StatusConstant.ENABLE);

        //根据当前员工id查询数据
        Employee employee = employeeMapper.getById(BaseContext.getCurrentId());

        //添加创建人姓名
        shopping.setEmployeeName(employee.getName());

        //TODO 使用AOP实现公共字段填充
        //设置当前记录的创建和修改时间，创建人id和修改人id
        shopping.setCreateTime(LocalDateTime.now());
        shopping.setUpdateTime(LocalDateTime.now());
        shopping.setCreateUser(BaseContext.getCurrentId());
        shopping.setUpdateUser(BaseContext.getCurrentId());

        shoppingMapper.insert(shopping);
    }

    /**
     * 商品分页，模糊匹配查询
     * @param shoppingQueryDTO
     * @return
     */
    public PageResult pageQuery(ShoppingQueryDTO shoppingQueryDTO) {
        //开始分页
        PageHelper.startPage(shoppingQueryDTO.getPage(),shoppingQueryDTO.getPageSize());

        Page<ShoppingQueryVO> page=shoppingMapper.pageQuery(shoppingQueryDTO);

        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 根据id获取商品信息
     * @param id
     * @return
     */
    public Shopping getInfoById(Long id) {
        return shoppingMapper.getById(id);
    }

    /**
     * 根据id删除商品信息
     * @param id
     */
    public void delete(Long id) {
        shoppingMapper.delete(id);
    }

    /**
     * 根据id修改信息
     * @param shopping
     */
    public void update(Shopping shopping) {
        //如果该商品的分类处于禁用，商品状态则不能修改为1
        Category category = categoryMapper.getById(shopping.getCategoryId());
        if(category.getStatus()==StatusConstant.DISABLE){
            //该分类禁用
            throw new BaseException(MessageConstant.CATEGORY_NOT_USE);
        }

        //设置当前记录修改时间，修改人id
        shopping.setUpdateTime(LocalDateTime.now());
        shopping.setUpdateUser(BaseContext.getCurrentId());

        shoppingMapper.update(shopping);
    }
}
