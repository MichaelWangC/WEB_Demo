package com.api.service.imp;

import com.api.beans.Supplier;
import com.api.mapper.SupplierMapper;
import com.api.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangc on 2017/8/23.
 */
@Service
@Transactional
public class SupplierServiceImp implements SupplierService {

    @Resource
    private SupplierMapper mapper;

    @Override
    public List<Supplier> findAllSupplier() {
        return mapper.findAllSupplier();
    }
}
