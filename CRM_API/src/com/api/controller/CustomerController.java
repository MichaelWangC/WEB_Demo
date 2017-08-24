package com.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Customer;
import com.api.service.CustomerService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wangc on 2017/8/24.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/addCustomer")
    public void addCustomer(Customer customer, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            customerService.addCustomer(customer);
            object.put("errcode","1");
            object.put("errmsg","创建成功");
        }catch (DuplicateKeyException e) {
            object.put("errcode","-1");
            object.put("errmsg", "客户名称已存在");
            e.printStackTrace();
        } catch (Exception e) {
            object.put("errcode","-1");
            object.put("errmsg", e.getMessage());
            e.printStackTrace();
        } finally {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            out.write(object.toJSONString());
        }
    }

    @RequestMapping("/customerList")
    public void getCustomerList(String start, String limit, String custname, HttpServletResponse response) {

    }
}
