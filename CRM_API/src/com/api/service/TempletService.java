package com.api.service;

import com.api.beans.NewTemplet;
import com.api.beans.NewTempletStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TempletService {
    String addTemplet(NewTemplet templet) throws Exception;
    List<NewTemplet> templetList(NewTemplet templet);
    void updateTempletStatus(NewTemplet templet, HttpServletRequest request) throws Exception;
    List<NewTempletStatus> getTempletStatusLog(String templetId) throws Exception;
}
