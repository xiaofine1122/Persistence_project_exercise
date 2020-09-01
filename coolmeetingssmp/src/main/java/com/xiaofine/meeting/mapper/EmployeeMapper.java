package com.xiaofine.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaofine.meeting.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xiaofine
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    Employee loadEmpByUsername(String username);

    Integer doReg(Employee employee);

    List<Employee> getEmpsByStatus(Integer status);

    Integer updateStatus(@Param("id") Integer employeeid,@Param("status") Integer status);

    List<Employee> getAllEmps(@Param("emp") Employee employee, @Param("page") Integer page, @Param("pagesize") Integer pageSize);

    List<Employee> getEmpsByDepId(Integer depId);

    Long getTotal(@Param("emp") Employee employee);
}
