package com.api.service.imp;

import com.api.beans.Customer;
import com.api.mapper.CustomerMapper;
import com.api.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by wangc on 2017/8/24.
 */
@Service
@Transactional
public class CustomerServiceImp implements CustomerService{

    @Resource
    private CustomerMapper mapper;

    @Override
    public void addCustomer(Customer customer) {
        mapper.addCustomer(customer);
    }

    @Override
    public void getCustomerList(String start, String limit, String custname) {

    }
}
