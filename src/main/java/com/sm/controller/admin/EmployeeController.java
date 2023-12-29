package com.sm.controller.admin;

import com.sm.constant.JwtClaimsConstant;
import com.sm.constant.MessageConstant;
import com.sm.dto.EmployeeInsertDTO;
import com.sm.dto.EmployeeLoginDTO;
import com.sm.dto.EmployeePasswordDTO;
import com.sm.dto.EmployeeQueryDTO;
import com.sm.entity.Employee;
import com.sm.properties.JwtProperties;
import com.sm.result.PageResult;
import com.sm.result.Result;
import com.sm.service.EmployeeService;
import com.sm.utils.JwtUtil;
import com.sm.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@Validated @RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录：{}",employeeLoginDTO);
        Employee employee=employeeService.login(employeeLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        //封装信息准备响应给前端
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();
        return Result.success(employeeLoginVO);
    }

    /**
     * 新增员工
     * @param employeeInsertDTO
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody EmployeeInsertDTO employeeInsertDTO){
        log.info("新增员工：{}",employeeInsertDTO);
        employeeService.insert(employeeInsertDTO);
        return Result.success();
    }

    /**
     * 获取当前员工数据
     * @return
     */
    @GetMapping("/info")
    public Result<Employee> getInfo(){
        log.info("获取当前员工数据");
        Employee employee = employeeService.getInfo();
        return Result.success(employee);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Employee employee){
        log.info("修改员工信息：{}",employee);
        employeeService.update(employee);
        return Result.success();
    }

    /**
     * 员工分页查询
     * @param employeeQueryDTO
     * @return
     */
    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody EmployeeQueryDTO employeeQueryDTO){
        log.info("员工分页查询:{}",employeeQueryDTO);
        PageResult pageResult=employeeService.pageQuery(employeeQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改当前员工密码
     * @param employeePasswordDTO
     * @return
     */
    @PostMapping("/password")
    public Result updatePassword(@RequestBody EmployeePasswordDTO employeePasswordDTO){
        log.info("修改员工密码：{}",employeePasswordDTO);
        employeeService.updatePassword(employeePasswordDTO);
        return Result.success();
    }

    /**
     * 根据id获取员工数据
     * @return
     */
    @GetMapping("/info/{id}")
    public Result<Employee> getInfoById(@PathVariable Long id){
        log.info("获取员工数据,id = {}",id);
        Employee employee = employeeService.getInfoById(id);
        return Result.success(employee);
    }

    /**
     * 根据id删除员工数据
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id){
        log.info("根据id删除员工,id = {}",id);
        employeeService.deleteById(id);
        return Result.success();
    }

}
