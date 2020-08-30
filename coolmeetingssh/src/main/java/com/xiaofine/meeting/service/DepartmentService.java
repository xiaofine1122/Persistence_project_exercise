package com.xiaofine.meeting.service;

import com.xiaofine.meeting.dao.DepartmentDao;
import com.xiaofine.meeting.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xiaofine
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public void save(Department department){
        departmentDao.save(department);
    }

    public void update(Department department){
        departmentDao.update(department);
    }

    public void delete(Integer id){
        departmentDao.delete(id);
    }

    public List<Department> findAll(){
        return departmentDao.findAll();
    }

    public Department findById(Integer id){
        return departmentDao.findById(id);
    }


}
