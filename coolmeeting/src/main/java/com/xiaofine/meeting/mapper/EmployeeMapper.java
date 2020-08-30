package com.xiaofine.meeting.mapper;

import com.xiaofine.meeting.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xiaofine
 */
public interface EmployeeMapper {
    Employee loadEmpByUsername(String username);

    Integer doReg(Employee employee);

    List<Employee> getEmpsByStatus(Integer status);

    Integer updateStatus(@Param("id") Integer employeeid,@Param("status") Integer status);

    List<Employee> getAllEmps(@Param("emp") Employee employee, @Param("page") Integer page, @Param("pagesize") Integer pageSize);

    List<Employee> getEmpsByDepId(Integer depId);

    Long getTotal(@Param("emp") Employee employee);
}
