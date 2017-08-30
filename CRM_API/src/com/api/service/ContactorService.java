package com.api.service;

import com.api.beans.Contactor;

/**
 * 联系人
 */
public interface ContactorService {
    String addContactor(Contactor contactor) throws Exception;

    boolean checkPassword(String mobileno, String password) throws Exception;

    Contactor getContactorByMobileno(String mobileno);
}
