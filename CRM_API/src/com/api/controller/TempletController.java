package com.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.NewTemplet;
import com.api.beans.NewTempletStatus;
import com.api.service.TempletService;
import com.api.util.ConstantUtil;
import com.api.util.TempletStatusManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/templet")
public class TempletController {

    @Resource
    private TempletService templetService;

    @RequestMapping("/addTemplet")
    public void addTemplet(NewTemplet templet, HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            String createdId = templet.getCreatedId();
            if (createdId == null || "".equals(createdId)) {
                createdId = (String) request.getSession().getAttribute(ConstantUtil.SESSION_USER_ID);
                templet.setCreatedId(createdId);
            }
            String templetid = templetService.addTemplet(templet);

            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "创建成功");
            object.put("templetid", templetid);
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

    @RequestMapping("/templetList")
    public void templetList(NewTemplet templet, HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            List<NewTemplet> list = templetService.templetList(templet);

            String supplierid = (String) request.getSession().getAttribute(ConstantUtil.SESSION_SUPPLIER_ID);
            boolean isSupplier = supplierid != null && !"".equals(supplierid);
            // 返回操作步骤
            for (NewTemplet templet1 : list) {
                String status = templet1.getStatus();
                String actions = TempletStatusManage.getTempletNextActionsByStatus(status, isSupplier);
                templet1.setActions(actions);
            }

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

    @RequestMapping("/updateTempletStatus")
    public void updateTempletStatus(NewTemplet templet, HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            templetService.updateTempletStatus(templet, request);

            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "执行成功");
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

    @RequestMapping("/getTempletStatusLog")
    public void getTempletStatusLog(String templetId, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            List<NewTempletStatus> list = templetService.getTempletStatusLog(templetId);

            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "创建成功");
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
