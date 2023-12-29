package com.sm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sm.constant.MessageConstant;
import com.sm.constant.PasswordConstant;
import com.sm.constant.StatusConstant;
import com.sm.constant.TypeConstant;
import com.sm.context.BaseContext;
import com.sm.dto.CategoryQueryDTO;
import com.sm.entity.Category;
import com.sm.entity.Employee;
import com.sm.entity.Shopping;
import com.sm.exception.BaseException;
import com.sm.mapper.CategoryMapper;
import com.sm.mapper.EmployeeMapper;
import com.sm.mapper.ShoppingMapper;
import com.sm.result.PageResult;
import com.sm.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ShoppingMapper shoppingMapper;

    /**
     * 分页,模糊匹配查询
     * @param categoryQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryQueryDTO categoryQueryDTO) {
        //开始分页
        PageHelper.startPage(categoryQueryDTO.getPage(),categoryQueryDTO.getPageSize());

        Page<Category> page=categoryMapper.pageQuery(categoryQueryDTO);

        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 根据id获取分类信息
     * @param id
     * @return
     */
    public Category infoById(Long id) {
        return categoryMapper.GetById(id);
    }

    /**
     * 新增分类
     * @param category
     */
    public void insert(Category category) {
        //根据名称查找分类
        Category categoryByName = categoryMapper.getByName(category.getName());
        if(categoryByName!=null){
            //该分类已存在
            throw new BaseException(MessageConstant.CATEGORY_EXISTS);
        }

        //根据当前员工id查询数据
        Employee employee = employeeMapper.getById(BaseContext.getCurrentId());

        //添加创建人姓名
        category.setEmployeeName(employee.getName());

        //TODO 使用AOP实现公共字段填充
        //设置当前记录的创建和修改时间，创建人id和修改人id
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    /**
     * 根据id删除分类
     * @param id
     */
    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 根据id修改分类信息
     * @param category
     */
    public void update(Category category) {
        //如果是将当前分类禁用，则归属于当前分类的商品都要禁用
        if(category.getStatus() == StatusConstant.DISABLE){
            //根据分类id修改所有该分类的商品的状态为0
            shoppingMapper.updateByCategoryId(category.getId());
        }

        //设置当前记录修改时间，修改人id
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.update(category);
    }

    /**
     * 获取所有分类信息
     * @return
     */
    public List<Category> getAll() {
        return categoryMapper.getAll();
    }
}
