package com.api.mapper;

import com.api.beans.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 */
@Mapper
public interface EmployeeMapper {
    void addEmployee(Employee employee);
    String getPassword(String mobileno);
    Employee getEmployeeByMobile(String mobileno);
}
