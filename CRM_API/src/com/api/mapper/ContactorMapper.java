package com.api.mapper;

import com.api.beans.Contactor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 联系人
 */
@Mapper
public interface ContactorMapper {
    void addContactor(@Param("contactor") Contactor contactor);
    String getPassword(String mobileno);
    Contactor getContactorByMobileno(String mobileno);
    List<Contactor> getContactorList(@Param("start") Integer start, @Param("limit") Integer limit, @Param("contactorName") String contactorName, @Param("customerId") Integer customerId);
}
