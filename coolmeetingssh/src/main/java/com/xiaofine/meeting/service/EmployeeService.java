package com.xiaofine.meeting.service;

import com.xiaofine.meeting.dao.EmployeeDao;
import com.xiaofine.meeting.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xiaofine
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void save(Employee employee){
        employeeDao.save(employee);
    }

    public void update(Employee employee){
        employeeDao.update(employee);
    }

    public void delete(Integer id){
        employeeDao.delete(id);
    }

    public List<Employee> findAll(){
        return employeeDao.findAll();
    }

    public Employee findById(Integer id){
        return employeeDao.findById(id);
    }

    public Employee doLogin(String username,String password){
        Employee employee = employeeDao.findByName(username);
        if(employee == null || !employee.getPassword().equals(password)){
            return null;
        }
        return employee;
    }

    public Integer doReg(Employee employee) {
        Employee emp = employeeDao.findByName(employee.getUsername());
        if (emp != null) {
            return -1;
        }
        employee.setRole(1);
        employee.setStatus(0);
        employeeDao.save(employee);
        Employee emp1= employeeDao.findByName(employee.getUsername());
        if (emp != null) {
            return 1;
        }
        return  -1;
    }

    public List<Employee> getEmpsByDepId(Integer depId){
        return employeeDao.getEmpsByDepId(depId);
    }


    public List<Employee> getEmpsByStatus(Integer status) {
        return employeeDao.getEmpsByStatus(status);
    }

    public void updateStatus(Integer employeeid, Integer status) {
        Employee emp = employeeDao.findById(employeeid);
        emp.setStatus(status);
        employeeDao.update(emp);
    }

    public List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize) {

        return employeeDao.getAllEmps(employee,page,pageSize);

    }

    public Integer getTotal(Employee employee) {
        return  employeeDao.getTotal(employee);
    }
}
