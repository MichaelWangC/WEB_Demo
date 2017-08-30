package com.api.service;

import com.api.beans.Customer;

/**
 * Created by wangc on 2017/8/24.
 */
public interface CustomerService {
    String addCustomer(Customer customer);
    void getCustomerList(String start, String limit, String custname);
}
