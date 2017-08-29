package com.api.beans;

/**
 * 字典
 */
public class Dictionary {
    private String dictId; // 字典id
    private String dictKey; // 字典key
    private String dictValue; // 字典值
    private String dictItem; //

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictItem() {
        return dictItem;
    }

    public void setDictItem(String dictItem) {
        this.dictItem = dictItem;
    }
}
