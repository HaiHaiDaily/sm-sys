package com.sm.mapper;

import com.github.pagehelper.Page;
import com.sm.dto.EmployeeQueryDTO;
import com.sm.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface EmployeeMapper {
    /**
     * 根据用户名查找员工信息
     * @param username
     * @return
     */
    @Select("select id, name, username, password, phone, sex, id_number, image, status, type, create_time, update_time, create_user, update_user from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    @Insert("insert into employee(name, username, password, phone, sex, id_number, image, status, type, create_time, update_time, create_user, update_user) VALUES " +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{image},#{status},#{type},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);

    /**
     * 根据id查询员工数据
     * @param employeeId
     * @return
     */
    @Select("select id, name, username, password, phone, sex, id_number, image, status, type, create_time, update_time, create_user, update_user from employee where id = #{employeeId}")
    Employee getById(Long employeeId);

    /**
     * 根据id修改员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 分页查询
     * @param employeeQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeeQueryDTO employeeQueryDTO);

    /**
     * 根据id删除员工信息
     * @param id
     */
    @Delete("delete from employee where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据动态条件统计员工数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
