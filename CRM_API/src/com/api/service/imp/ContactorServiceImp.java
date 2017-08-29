package com.api.service.imp;

import com.api.beans.Contactor;
import com.api.mapper.ContactorMapper;
import com.api.service.ContactorService;
import com.api.util.ConstantUtil;
import com.api.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ContactorServiceImp implements ContactorService {

    @Resource
    private ContactorMapper contactorMapper;

    @Override
    public String addContactor(Contactor contactor) throws Exception {
        // 必填项判断
        String mobileno = contactor.getContactorMobile();
        if (mobileno == null || "".equals(mobileno)) throw new Exception("请输入手机号");
        String contactorname = contactor.getContactorName();
        if (contactorname == null || "".equals(contactorname)) throw new Exception("请输入姓名");

        // 密码处理
        String password = contactor.getPassword();
        if (password == null || "".equals(password)) {
            password = ConstantUtil.INIT_PASSWORD;
        }
        String md5Pwd = SecurityUtil.getMD5Pwd(password);
        contactor.setPassword(md5Pwd);

        // 添加联系人
        contactorMapper.addContactor(contactor);
        return contactor.getContactorId();
    }
}
