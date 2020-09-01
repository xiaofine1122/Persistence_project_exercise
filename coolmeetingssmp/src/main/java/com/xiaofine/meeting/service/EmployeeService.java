package com.xiaofine.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaofine.meeting.model.Employee;

/**
 * @author: xiaofine
 */
public interface EmployeeService extends IService<Employee> {
    public Employee doLogin(String username,String password);
    public Integer doReg(Employee employee);

}
