package com.xiaofine.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaofine.meeting.mapper.EmployeeMapper;
import com.xiaofine.meeting.model.Employee;
import com.xiaofine.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiaofine
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

        @Autowired
        EmployeeMapper employeeMapper;

        public Employee doLogin(String username,String password){
            Employee employee = employeeMapper.selectOne(new QueryWrapper<Employee>().lambda().eq(Employee::getUsername,username));
            if(employee == null || !employee.getPassword().equals(password)){
                return null;
            }
            return employee;
        }

        public Integer doReg(Employee employee) {
            Employee emp = employeeMapper.selectOne(new QueryWrapper<Employee>().lambda().eq(Employee::getUsername,employee.getUsername()));
            if (emp != null) {
                return -1;
            }
            employee.setRole(1);
            employee.setStatus(0);
            return employeeMapper.insert(employee);

    }
}
