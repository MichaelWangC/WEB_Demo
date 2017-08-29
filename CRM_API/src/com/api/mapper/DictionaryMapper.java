package com.api.mapper;

import com.api.beans.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangc on 2017/8/2.
 */
@Mapper
public interface DictionaryMapper {
    List<Dictionary> getDictionary(String item);
}
