package com.api.service.imp;

import com.api.beans.Contactor;
import com.api.mapper.ContactorMapper;
import com.api.service.ContactorService;
import com.api.util.ConstantUtil;
import com.api.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
        String customerId = contactor.getCustomerId();
        if (customerId == null || "".equals(customerId)) throw new Exception("请传入客户编号");

        // 密码处理
        String password = contactor.getPassword();
        if (password == null || "".equals(password)) {
            password = ConstantUtil.INIT_PASSWORD;
        }
        String roleId = contactor.getRoleId();
        if (roleId == null || "".equals(roleId)) {
            roleId = ConstantUtil.ROLE_ID_MEMBERS;
            contactor.setRoleId(roleId);
        }
        String md5Pwd = SecurityUtil.getMD5Pwd(password);
        contactor.setPassword(md5Pwd);

        // 添加联系人
        contactorMapper.addContactor(contactor);
        return contactor.getContactorId();
    }

    @Override
    public boolean checkPassword(String mobileno, String password) throws Exception {
        if (mobileno == null || "".equals(mobileno)) throw new Exception("手机号不能为空");
        if (password == null || "".equals(password)) throw new Exception("密码不能为空");

        String tpassword = contactorMapper.getPassword(mobileno);
        if (tpassword == null || "".equals(tpassword)) return false;

        String passwordMd5 = SecurityUtil.getMD5Pwd(password);
        return tpassword.equals(passwordMd5);
    }

    @Override
    public Contactor getContactorByMobileno(String mobileno) {
        return contactorMapper.getContactorByMobileno(mobileno);
    }

    @Override
    public List<Contactor> getContactorList(Integer start, Integer limit, String contactorName, Integer customerId) {
        if (contactorName != null && !"".equals(contactorName)) {
            contactorName = "%"+contactorName+"%";
        }
        if (start == null) {
            start = 0;
        } else {
            start = (start-1)*limit;
        }

        if (limit == null) {
            limit = 20;
        }
        return contactorMapper.getContactorList(start, limit, contactorName, customerId);
    }
}
