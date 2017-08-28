package com.api.controller.supplier;

import com.alibaba.fastjson.JSONObject;
import com.api.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        object.put("errcode", ConstantUtil.ERROR_CODE_OK);
        object.put("errmsg", "供应商登录");
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
