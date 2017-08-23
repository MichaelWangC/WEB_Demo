package com.api.controller;

import com.api.beans.Supplier;
import com.api.service.SupplierService;
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
        String allSupplier = list.toString();
//        Json
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(allSupplier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
