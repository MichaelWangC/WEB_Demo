package com.api.service;

import com.api.beans.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> getDictionary(String item);
}
