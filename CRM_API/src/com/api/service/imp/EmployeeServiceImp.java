package com.api.service.imp;

import com.api.beans.Employee;
import com.api.mapper.EmployeeMapper;
import com.api.service.EmployeeService;
import com.api.util.ConstantUtil;
import com.api.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{

    @Resource
    private EmployeeMapper mapper;

    @Override
    public String addEmployee(Employee employee) throws Exception {
        // 必填项判断
        String mobileno = employee.getEmployeeMobile();
        if (mobileno == null || "".equals(mobileno)) throw new Exception("请输入手机号");
        String empname = employee.getEmployeeName();
        if (empname == null || "".equals(empname)) throw new Exception("请输入姓名");

        // 密码处理
        String password = employee.getPassword();
        if (password == null || "".equals(password)) {
            password = ConstantUtil.INIT_PASSWORD;
        }
        String md5Pwd = SecurityUtil.getMD5Pwd(password);
        employee.setPassword(md5Pwd);

        // 数据了录入
        mapper.addEmployee(employee);
        return employee.getEmployeeId();
    }

    @Override
    public boolean checkPassword(String mobileno, String password) throws Exception {
        if (mobileno == null || "".equals(mobileno)) throw new Exception("手机号不能为空");
        if (password == null || "".equals(password)) throw new Exception("密码不能为空");

        String passwordS = mapper.getPassword(mobileno);
        if (passwordS == null || "".equals(passwordS)) return false;

        String passwordMd5 = SecurityUtil.getMD5Pwd(password);
        return passwordS.equals(passwordMd5);
    }

    @Override
    public Employee getEmployeeByMobile(String mobileno) {
        return mapper.getEmployeeByMobile(mobileno);
    }
}
