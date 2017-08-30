package com.api.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Contactor;
import com.api.beans.Customer;
import com.api.service.ContactorService;
import com.api.service.CustomerService;
import com.api.util.ConstantUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private ContactorService contactorService;

    @RequestMapping("/addCustomer")
    public void addCustomer(Customer customer, Contactor contactor, HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;

        try {
            out = response.getWriter();
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            // 必填项校验
            String customerName = customer.getCustomerName();
            if (customerName == null || "".equals(customerName)) throw new Exception("请传入客户名称");
            String mobileNo = contactor.getContactorMobile();
            if (mobileNo == null || "".equals(mobileNo)) throw new Exception("请传入联系人手机号");
            String contactorname = contactor.getContactorName();
            if (contactorname == null || "".equals(contactorname)) throw new Exception("请传入联系人姓名");

            // 添加客户
            String supplierId = (String) request.getSession().getAttribute(ConstantUtil.SESSION_SUPPLIER_ID);
            if (supplierId == null || "".equals(supplierId)) {
                throw new Exception("未找到供应商编号，请登录或联系系统管理员");
            } else {
                customer.setOwnerId(supplierId);
            }

            String customerId = customerService.addCustomer(customer);
            if (customerId == null || "".equals(customerId)) {
                throw new Exception("客户创建失败，未返回客户编号");
            } else {
                // 添加联系人
                try {
                    contactor.setCustomerId(customerId);
                    String roleId = contactor.getRoleId();
                    if (roleId == null || "".equals(roleId)) {
                        // 默认 从客户添加的 联系人 级别为领导
                        contactor.setRoleId(ConstantUtil.ROLE_ID_ADMINS);
                    }
                    // 添加
                    String tcontactorid = contactorService.addContactor(contactor);
                    if (tcontactorid == null || "".equals(tcontactorid)) {
                        object.put("errcode", ConstantUtil.ERROR_CODE_OK);
                        object.put("errmsg", "客户创建成功,但联系人编号未返回");
                        object.put("customerid", customerId);
                    } else {
                        object.put("errcode", ConstantUtil.ERROR_CODE_OK);
                        object.put("errmsg", "创建成功");
                        object.put("customerid", customerId);
                    }
                } catch (DuplicateKeyException e) {
                    // 应该添加客户前校验，后续再修改
                    object.put("errcode", ConstantUtil.ERROR_CODE_OK);
                    object.put("errmsg", "客户创建成功,但联系人号码已存在");
                } catch (Exception e) {
                    object.put("errcode", ConstantUtil.ERROR_CODE_OK);
                    object.put("errmsg", e.getMessage());
                } finally {
                    out.write(object.toJSONString());
                }
            }
        } catch (DuplicateKeyException e) {
            object.put("errcode", ConstantUtil.ERROR_CODE_FAIL);
            object.put("errmsg", "客户名称已存在");
            e.printStackTrace();
            out.write(object.toJSONString());
        } catch (Exception e) {
            object.put("errcode", ConstantUtil.ERROR_CODE_FAIL);
            object.put("errmsg", e.getMessage());
            e.printStackTrace();
            out.write(object.toJSONString());
        }
    }

    @RequestMapping("/customerList")
    public void getCustomerList(Integer start, Integer limit, String custname, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "创建成功");
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
