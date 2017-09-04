package com.api.mapper;


import com.api.beans.NewTemplet;
import com.api.beans.NewTempletStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TempletListMapper {
    void addTemplet(NewTemplet templet);
    List<NewTemplet> templetList(NewTemplet templet);
    void updateTempletStatus(NewTemplet templet);
    void updateTempletStatusLog(@Param("changeInfo") String changeInfo, @Param("templetId") String templetId);
    List<NewTempletStatus> getTempletStatusLog(String templetId);
}
