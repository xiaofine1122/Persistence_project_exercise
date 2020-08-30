package com.xiaofine.meeting.dao;

import com.xiaofine.meeting.dao.base.BaseDaoImpl;
import com.xiaofine.meeting.pojo.Department;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: xiaofine
 */
@Repository("departmentDao")
@Transactional
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {
    public Class getEntityClass() {
        return Department.class;
    }
}
