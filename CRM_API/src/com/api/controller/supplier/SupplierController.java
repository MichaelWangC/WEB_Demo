package com.api.controller.supplier;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Supplier;
import com.api.service.SupplierService;
import com.api.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by wangc on 2017/8/23.
 * 供应商
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    /**
     * 获取所有供应商
     */
    @RequestMapping("/getAllSupplier")
    public void getAllSupplier(HttpServletResponse response){
        List<Supplier> list = supplierService.findAllSupplier();

        JSONObject object = new JSONObject();
        object.put("errcode", ConstantUtil.ERROR_CODE_OK);
        object.put("errmsg", "ok");
        object.put("list",list);
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
