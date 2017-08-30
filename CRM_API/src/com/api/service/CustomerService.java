package com.api.service;

import com.api.beans.Customer;

import java.util.List;

/**
 * Created by wangc on 2017/8/24.
 */
public interface CustomerService {
    String addCustomer(Customer customer);
    List<Customer> getCustomerList(Integer start, Integer limit, String custname, String ownerId, String customerId);
}
