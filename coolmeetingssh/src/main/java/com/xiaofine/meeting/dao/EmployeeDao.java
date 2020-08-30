package com.xiaofine.meeting.dao;

import com.xiaofine.meeting.dao.base.BaseDao;
import com.xiaofine.meeting.pojo.Employee;

import java.util.List;

/**
 * @author: xiaofine
 */
public interface EmployeeDao extends BaseDao<Employee> {

    public Employee findByName(String name);

    List<Employee> getEmpsByDepId(Integer depId);

    List<Employee> getEmpsByStatus(Integer status);

    List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize);

    Integer getTotal(Employee employee);
}
