package com.api.mapper;

import com.api.beans.Contactor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 联系人
 */
@Mapper
public interface ContactorMapper {
    void addContactor(@Param("contactor") Contactor contactor);
}
