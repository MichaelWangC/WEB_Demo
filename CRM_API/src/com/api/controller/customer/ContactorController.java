package com.api.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Contactor;
import com.api.service.ContactorService;
import com.api.util.ConstantUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * 联系人
 */
@Controller
@RequestMapping("/contactor")
public class ContactorController {

    @Resource
    private ContactorService contactorService;
    /**
     * 添加联系人
     */
    @RequestMapping("/addContactor")
    public void addContactor(Contactor contactor, HttpServletResponse response) {

        // 添加联系人
        JSONObject object = new JSONObject();
        PrintWriter out = null;

        try {
            out = response.getWriter();

            String contactorid = contactorService.addContactor(contactor);
            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "创建成功");
            object.put("contactorid", contactorid);
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

    @RequestMapping("/contactorList")
    public void getContactorList(Integer start, Integer limit, String contactorName, Integer customerId, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            List<Contactor> list = contactorService.getContactorList(start, limit, contactorName, customerId);
            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "ok");
            object.put("list", list);
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
