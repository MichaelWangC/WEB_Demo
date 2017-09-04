package com.api.service.imp;

import com.api.beans.NewTemplet;
import com.api.beans.NewTempletStatus;
import com.api.mapper.TempletListMapper;
import com.api.service.TempletService;
import com.api.util.ConstantUtil;
import com.api.util.TempletStatusManage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class TempletServiceImp implements TempletService{

    @Resource
    private TempletListMapper templetListMapper;

    @Override
    public String addTemplet(NewTemplet templet) throws Exception {

        String supplierId = templet.getSupplierId();
        if (supplierId == null && "".equals(supplierId)) {
            throw new Exception("请传入供应商编号");
        }

        String customerId = templet.getCustomerId();
        if (customerId == null && "".equals(customerId)) {
            throw new Exception("请传入客户编号");
        }

        String contactorId = templet.getContactorId();
        if (contactorId == null && "".equals(contactorId)) {
            throw new Exception("请传入联系人编号");
        }

        templet.setStatus("已接单"); // 默认状态
        templetListMapper.addTemplet(templet);
        return templet.getTempletId();
    }

    @Override
    public List<NewTemplet> templetList(NewTemplet templet) {
        Integer start = templet.getStart();
        Integer limit = templet.getLimit();
        if (start == null) {
            start = 0;
        } else {
            start = (start-1)*limit;
        }

        if (limit == null) {
            limit = 20;
        }
        templet.setStart(start);
        templet.setLimit(limit);
        return templetListMapper.templetList(templet);
    }

    @Override
    public void updateTempletStatus(NewTemplet templet, HttpServletRequest request) throws Exception {
        String templetId = templet.getTempletId();
        if (templetId == null || "".equals(templetId)) {
            throw new Exception("请传入样板id");
        }
        String actions = templet.getActions();
        if (actions == null || "".equals(actions)) {
            throw new Exception("请传入样板操作步骤");
        }
        String status = TempletStatusManage.getTempletStatusByAction(actions);
        if (TempletStatusManage.TEMPLET_STATUS_ERROR.equals(status)) {
            throw new Exception("没有该操作步骤");
        }
        templet.setStatus(status);
        templetListMapper.updateTempletStatus(templet);


        String userName = (String) request.getSession().getAttribute(ConstantUtil.SESSION_USER_NAME);
        String userId = (String) request.getSession().getAttribute(ConstantUtil.SESSION_USER_ID);

        String supplierid = (String) request.getSession().getAttribute(ConstantUtil.SESSION_SUPPLIER_ID);
        boolean isSupplier = supplierid != null && !"".equals(supplierid);
        String orgName;
        if (isSupplier) {
            orgName = (String) request.getSession().getAttribute(ConstantUtil.SESSION_SUPPLIER_NAME);
        } else {
            orgName = (String) request.getSession().getAttribute(ConstantUtil.SESSION_CUSTOMER_NAME);
        }

        String changeInfo = orgName + " " + userName + "(" + userId + ") 执行'" + actions + "'步骤,状态变更为" + status;
        templetListMapper.updateTempletStatusLog(changeInfo, templetId);
    }

    @Override
    public List<NewTempletStatus> getTempletStatusLog(String templetId) throws Exception {
        if (templetId == null && "".equals(templetId)) {
            throw new Exception("请传入样板id");
        }
        return templetListMapper.getTempletStatusLog(templetId);
    }
}
