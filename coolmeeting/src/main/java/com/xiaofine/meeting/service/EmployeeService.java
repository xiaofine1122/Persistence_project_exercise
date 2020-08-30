package com.xiaofine.meeting.service;

import com.xiaofine.meeting.mapper.EmployeeMapper;
import com.xiaofine.meeting.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xiaofine
 */
@Service
public class EmployeeService {

    @Autowired
        EmployeeMapper employeeMapper;

        public Employee doLogin(String username,String password){
            Employee employee = employeeMapper.loadEmpByUsername(username);
            if(employee == null || !employee.getPassword().equals(password)){
                return null;
            }
            return employee;
        }

        public Integer doReg(Employee employee) {
            Employee emp = employeeMapper.loadEmpByUsername(employee.getUsername());
            if (emp != null) {
                return -1;
            }
        employee.setRole(1);
        employee.setStatus(0);
        return employeeMapper.doReg(employee);

    }

    public List<Employee> getEmpsByStatus(Integer status) {
        return employeeMapper.getEmpsByStatus(status);
    }

    public Integer updateStatus(Integer employeeid, Integer status) {
       return employeeMapper.updateStatus(employeeid,status);
    }

    public List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        return employeeMapper.getAllEmps(employee,page,pageSize);
    }

    public List<Employee> getEmpsByDepId(Integer depId) {
        return employeeMapper.getEmpsByDepId(depId);
    }

    public Long getTotal(Employee employee) {
            return employeeMapper.getTotal(employee);
    }
}
