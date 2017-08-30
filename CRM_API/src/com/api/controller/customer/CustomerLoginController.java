package com.api.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Contactor;
import com.api.service.ContactorService;
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
 * 客户登录
 */
@Controller
@RequestMapping("/customer")
public class CustomerLoginController {

    @Resource
    private ContactorService contactorService;

    @RequestMapping("/login")
    public void login(String mobileno, String password, HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;

        try {
            out = response.getWriter();
            boolean checkPwd = contactorService.checkPassword(mobileno, password);
            if (checkPwd) {
                object.put("errcode", ConstantUtil.ERROR_CODE_OK);
                object.put("errmsg", "登录成功");
                // 获取员工所属供应商 员工编号等信息
                Contactor contactor = contactorService.getContactorByMobileno(mobileno);
                object.put("contactor",contactor);
                // 保存SESSION
                request.getSession().setAttribute(ConstantUtil.SESSION_USER_ID, contactor.getContactorId());
                request.getSession().setAttribute(ConstantUtil.SESSION_USER_NAME, contactor.getContactorName());
                request.getSession().setAttribute(ConstantUtil.SESSION_CUSTOMER_ID, contactor.getCustomerId());
                request.getSession().setAttribute(ConstantUtil.SESSION_CUSTOMER_NAME, contactor.getCustomerName());
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
