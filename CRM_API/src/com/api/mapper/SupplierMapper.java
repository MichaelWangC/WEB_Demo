package com.api.mapper;

import com.api.beans.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangc on 2017/8/23.
 */
@Mapper
public interface SupplierMapper {
    List<Supplier> findAllSupplier();
}
