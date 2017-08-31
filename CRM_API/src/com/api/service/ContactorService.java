package com.api.service;

import com.api.beans.Contactor;

import java.util.List;

/**
 * 联系人
 */
public interface ContactorService {
    String addContactor(Contactor contactor) throws Exception;

    boolean checkPassword(String mobileno, String password) throws Exception;

    Contactor getContactorByMobileno(String mobileno);

    List<Contactor> getContactorList(Integer start, Integer limit, String contactorName, Integer customerId);
}
