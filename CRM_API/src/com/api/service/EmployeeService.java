package com.api.service;

import com.api.beans.Employee;

public interface EmployeeService {
    String addEmployee(Employee employee) throws Exception;

    boolean checkPassword(String mobileno, String password) throws Exception;

    Employee getEmployeeByMobile(String mobileno);
}
