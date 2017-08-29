package com.api.service.imp;

import com.api.beans.Dictionary;
import com.api.mapper.DictionaryMapper;
import com.api.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DictionaryServiceImp implements DictionaryService {

    @Resource
    private DictionaryMapper mapper;

    @Override
    public List<Dictionary> getDictionary(String item) {
        return mapper.getDictionary(item);
    }
}
