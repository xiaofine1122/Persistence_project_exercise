package com.xiaofine.meeting.dao;

import com.xiaofine.meeting.dao.base.BaseDaoImpl;
import com.xiaofine.meeting.pojo.Employee;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: xiaofine
 */
@Repository("employeeDao")
@Transactional
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

    public Class getEntityClass() {
        return Employee.class;
    }

    public Employee findByName(String name) {
        String hsql="FROM Employee where username=?";
        System.out.println(hsql);
        Query query = getSession().createQuery(hsql);
        query.setString(0,name);
        Employee employee = (Employee)query.uniqueResult();
        return employee;
    }

    public List<Employee> getEmpsByDepId(Integer depId) {
        String hsql="FROM Employee where departmentid=?";
        System.out.println(hsql);
        Query query = getSession().createQuery(hsql);
        query.setInteger(0,depId);
        List<Employee> employees = (List<Employee>) query.uniqueResult();
        return employees;
    }

    public List<Employee> getEmpsByStatus(Integer status) {
        String hsql="FROM Employee where status=?";
        System.out.println(hsql);
        Query query = getSession().createQuery(hsql);
        query.setInteger(0,status);
        List<Employee> employees = (List<Employee>) query.uniqueResult();
        return employees;
    }

    public List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize) {
        Criteria ctr = getSession().createCriteria(Employee.class);
        ctr.add(Expression.eq("status",employee.getStatus()));
        if(employee.getEmployeename()!=null && employee.getEmployeename().length()>0){
            ctr.add(Expression.like("employeename",employee.getEmployeename()));
        }
        if(employee.getUsername()!=null && employee.getUsername().length()>0){
            ctr.add(Expression.like("username",employee.getUsername()));
        }

        int firstRow = (page - 1) * pageSize;// 获取第一行
        ctr.setFirstResult(firstRow);
        ctr.setMaxResults(pageSize);// 每页显示的行数
        List list = ctr.list();
        return list;

    }

    public Integer getTotal(Employee employee) {
        Criteria ctr = getSession().createCriteria(Employee.class);
        ctr.add(Expression.eq("status",employee.getStatus()));
        if(employee.getEmployeename()!=null && employee.getEmployeename().length()>0){
            ctr.add(Expression.like("employeename",employee.getEmployeename()));
        }
        if(employee.getUsername()!=null && employee.getUsername().length()>0){
            ctr.add(Expression.like("username",employee.getUsername()));
        }
        List list = ctr.list();
        return list.size();
    }
}
