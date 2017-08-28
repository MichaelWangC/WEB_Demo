package com.api.filter;

import com.alibaba.fastjson.JSONObject;
import com.api.util.ConstantUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器 处理是否已登录
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String userId = (String) httpServletRequest.getSession().getAttribute(ConstantUtil.SESSION_USER_ID);
        if (userId == null || "".equals(userId)) {
            JSONObject object = new JSONObject();
            object.put("errcode", ConstantUtil.ERROR_CODE_LOGIN);
            object.put("errmsg","未登录或长时间未操作，请登录");
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("text/html; charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.write(object.toJSONString());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
