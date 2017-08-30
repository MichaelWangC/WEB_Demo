package com.api.controller.supplier;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Employee;
import com.api.service.EmployeeService;
import com.api.util.ConstantUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *员工
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    /**
     * 添加员工
     * @param employee
     * @param response
     */
    @RequestMapping("/addEmployee")
    public void addEmployee(Employee employee, HttpServletResponse response) {
        // 添加员工
        JSONObject object = new JSONObject();
        PrintWriter out = null;

        try {
            out = response.getWriter();

            String employeeid = employeeService.addEmployee(employee);
            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "创建成功");
            object.put("employeeid", employeeid);
        } catch (DuplicateKeyException e) {
            object.put("errcode", ConstantUtil.ERROR_CODE_FAIL);
            object.put("errmsg", "该手机号码已被注册");
            e.printStackTrace();
        } catch (Exception e) {
            object.put("errcode", ConstantUtil.ERROR_CODE_FAIL);
            object.put("errmsg", e.getMessage());
            e.printStackTrace();
        } finally {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            out.write(object.toJSONString());
        }
    }
}
