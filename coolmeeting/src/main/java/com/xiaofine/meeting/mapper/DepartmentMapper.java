package com.xiaofine.meeting.mapper;

import com.xiaofine.meeting.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xiaofine
 */
public interface DepartmentMapper {
    Department getDeptById(Integer id);

    List<Department> getAllDeps();

    Integer adddepartment(String departmentname);

    Department getDepByName(String departmentname);

    Integer deletedep(Integer departmentid);

    Integer updatedep(@Param("id") Integer id,@Param("name") String name);
}
