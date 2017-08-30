package com.api.mapper;

import com.api.beans.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangc on 2017/8/24.
 */
@Mapper
public interface CustomerMapper {
    boolean addCustomer(Customer customer);
    List<Customer> getCustomerList(@Param("start") Integer start, @Param("limit") Integer limit, @Param("custname") String custname, @Param("ownerId") String ownerId, @Param("customerId") String customerId);
}
