package com.api.mapper;

import com.api.beans.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangc on 2017/8/24.
 */
@Mapper
public interface CustomerMapper {
    boolean addCustomer(Customer customer);
    void getCustomerList(String start, String limit, String custname);
}
