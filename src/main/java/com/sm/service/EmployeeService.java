package com.sm.service;

import com.sm.dto.EmployeeInsertDTO;
import com.sm.dto.EmployeeLoginDTO;
import com.sm.dto.EmployeePasswordDTO;
import com.sm.dto.EmployeeQueryDTO;
import com.sm.entity.Employee;
import com.sm.result.PageResult;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeInsertDTO
     */
    void insert(EmployeeInsertDTO employeeInsertDTO);

    /**
     * 获取当前员工数据
     */
    Employee getInfo();

    /**
     * 根据id修改员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 员工分页查询
     * @param employeeQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeeQueryDTO employeeQueryDTO);

    /**
     * 修改当前员工密码
     * @param employeePasswordDTO
     */
    void updatePassword(EmployeePasswordDTO employeePasswordDTO);

    /**
     * 根据id获取员工数据
     */
    Employee getInfoById(Long id);

    /**
     * 根据id删除员工
     * @param id
     */
    void deleteById(Long id);
}
