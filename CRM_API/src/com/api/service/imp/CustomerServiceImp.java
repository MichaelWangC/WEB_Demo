package com.api.service.imp;

import com.api.beans.Customer;
import com.api.mapper.CustomerMapper;
import com.api.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangc on 2017/8/24.
 */
@Service
@Transactional
public class CustomerServiceImp implements CustomerService{

    @Resource
    private CustomerMapper mapper;

    @Override
    public String addCustomer(Customer customer) {
        mapper.addCustomer(customer);
        return  customer.getCustomerId();
    }

    @Override
    public List<Customer> getCustomerList(Integer start, Integer limit, String custname, String ownerId, String customerId) {
        if (custname != null && !"".equals(custname)) {
            custname = "%"+custname+"%";
        }
        if (start == null) {
            start = 0;
        } else {
            start = (start-1)*limit;
        }

        if (limit == null) {
            limit = 20;
        }

        return mapper.getCustomerList(start, limit, custname, ownerId, customerId);
    }
}
