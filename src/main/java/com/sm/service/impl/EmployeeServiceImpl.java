package com.sm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sm.constant.MessageConstant;
import com.sm.constant.PasswordConstant;
import com.sm.constant.StatusConstant;
import com.sm.constant.TypeConstant;
import com.sm.context.BaseContext;
import com.sm.dto.EmployeeInsertDTO;
import com.sm.dto.EmployeeLoginDTO;
import com.sm.dto.EmployeePasswordDTO;
import com.sm.dto.EmployeeQueryDTO;
import com.sm.entity.Employee;
import com.sm.exception.BaseException;
import com.sm.mapper.EmployeeMapper;
import com.sm.result.PageResult;
import com.sm.service.EmployeeService;
import com.sm.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new BaseException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //把前端传来的明文密码进行md5加密
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new BaseException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new BaseException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     * @param employeeInsertDTO
     */
    public void insert(EmployeeInsertDTO employeeInsertDTO) {
        //根据用户名查找员工
        Employee employee = employeeMapper.getByUsername(employeeInsertDTO.getUsername());
        if(employee!=null){
            //该用户名已存在
            throw new BaseException(MessageConstant.ACCOUNT_EXISTS);
        }

        //创建一个员工实体类
        employee=new Employee();

        //对象属性拷贝 前对象属性copy到后一个对象属性
        BeanUtils.copyProperties(employeeInsertDTO,employee);

        //设置账号的状态, 默认正常状态1表示正常0表示锁定
        employee.setStatus(StatusConstant.ENABLE);

        //设置账号权限，默认员工为2
        employee.setType(TypeConstant.EMPLOYEE);

        //设置密码,默认密码123456,要用md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //TODO 使用AOP实现公共字段填充
        //设置当前记录的创建和修改时间，创建人id和修改人id
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        //调用持久层接口
        employeeMapper.insert(employee);
    }

    /**
     * 获取当前员工数据
     */
    public Employee getInfo() {
        //获取当前员工id
        Long employeeId = BaseContext.getCurrentId();

        //根据id查询员工信息
        Employee employee = employeeMapper.getById(employeeId);

        return employee;
    }

    /**
     * 根据id修改员工信息
     * @param employee
     */
    public void update(Employee employee) {
        //设置当前记录修改时间，修改人id
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.update(employee);
    }

    /**
     * 员工分页查询
     * @param employeeQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeeQueryDTO employeeQueryDTO) {
        //开始分页
        PageHelper.startPage(employeeQueryDTO.getPage(),employeeQueryDTO.getPageSize());

        Page<Employee> page=employeeMapper.pageQuery(employeeQueryDTO);

        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 修改当前员工密码
     * @param employeePasswordDTO
     */
    public void updatePassword(EmployeePasswordDTO employeePasswordDTO) {
        //根据当前员工id查询员工数据
        Employee employee = employeeMapper.getById(BaseContext.getCurrentId());

        //1.校验原密码
        if(!employee.getPassword().equals(MD5Utils.getMD5(employeePasswordDTO.getOldPassword()))){
            //原密码错误
            throw new BaseException(MessageConstant.OLD_PASSWORD_ERROR);
        }

        //2.原密码与新密码不能一致
        if(employeePasswordDTO.getOldPassword().equals(employeePasswordDTO.getNewPassword())){
            //原密码与新密码不能一致
            throw new BaseException(MessageConstant.OLD_AND_NEW_PASSWORD_NOT_SAME);
        }

        //设置新密码
        employee.setPassword(MD5Utils.getMD5(employeePasswordDTO.getNewPassword()));

        //TODO 使用AOP实现公共字段填充
        //设置当前记录的修改时间，修改人id
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.update(employee);
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    public Employee getInfoById(Long id) {
        //根据id查询员工信息
        Employee employee = employeeMapper.getById(id);

        return employee;
    }

    /**
     * 根据id删除员工
     * @param id
     */
    public void deleteById(Long id) {
        employeeMapper.deleteById(id);
    }


}
