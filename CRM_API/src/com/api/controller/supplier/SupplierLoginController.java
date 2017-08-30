package com.api.controller.supplier;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Employee;
import com.api.service.EmployeeService;
import com.api.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wangc on 2017/8/28.
 * 供应商登录
 */
@Controller
@RequestMapping("/supplier")
public class SupplierLoginController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/login")
    public void login(String mobileno, String password, HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;

        try {
            out = response.getWriter();
            boolean checkPwd = employeeService.checkPassword(mobileno, password);
            if (checkPwd) {
                object.put("errcode", ConstantUtil.ERROR_CODE_OK);
                object.put("errmsg", "登录成功");
                // 获取员工所属供应商 员工编号等信息
                Employee employee = employeeService.getEmployeeByMobile(mobileno);
                object.put("employee", employee);
                // 保存SESSION
                request.getSession().setAttribute(ConstantUtil.SESSION_USER_ID, employee.getEmployeeId());
                request.getSession().setAttribute(ConstantUtil.SESSION_USER_NAME, employee.getEmployeeName());
                request.getSession().setAttribute(ConstantUtil.SESSION_SUPPLIER_ID, employee.getSupplierId());
                request.getSession().setAttribute(ConstantUtil.SESSION_SUPPLIER_NAME, employee.getSupplierName());
            } else {
                object.put("errcode", ConstantUtil.ERROR_CODE_FAIL);
                object.put("errmsg", "手机号或密码不正确");
            }
        } catch (Exception e) {
            object.put("errcode", ConstantUtil.ERROR_CODE_FAIL);
            object.put("errmsg", e.getMessage());
        } finally {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            out.write(object.toJSONString());
        }
    }
}
