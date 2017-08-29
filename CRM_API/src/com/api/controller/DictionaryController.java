package com.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.beans.Dictionary;
import com.api.service.DictionaryService;
import com.api.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典获取
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 获取字典
     */
    @RequestMapping("/getDictionary")
    public void getDictionary(String item, HttpServletResponse response){

        JSONObject object = new JSONObject();
        PrintWriter out = null;
        try {
            out = response.getWriter();

            List<Dictionary> list = dictionaryService.getDictionary(item);

            object.put("errcode", ConstantUtil.ERROR_CODE_OK);
            object.put("errmsg", "ok");

            // 返回参数 转换
            List<Map<String,String>> dicts = new ArrayList();
            for (Dictionary dict: list) {
                Map<String,String> map = new HashMap();
                map.put("key", dict.getDictKey());
                map.put("value", dict.getDictValue());

                dicts.add(map);
            }
            object.put("list", dicts);

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
