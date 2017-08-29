package com.api.beans;

/**
 * 联系人
 */
public class Contactor {
    private String contactorId;
    private String contactorName;
    private String contactorMobile;
    private String createTime;
    private String modifyTime;
    private String password;
    private String creatorId;
    private String customerId;
    private String contactorLevel;
    private String roleId;

    public String getContactorId() {
        return contactorId;
    }

    public void setContactorId(String contactorId) {
        this.contactorId = contactorId;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }

    public String getContactorMobile() {
        return contactorMobile;
    }

    public void setContactorMobile(String contactorMobile) {
        this.contactorMobile = contactorMobile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContactorLevel() {
        return contactorLevel;
    }

    public void setContactorLevel(String contactorLevel) {
        this.contactorLevel = contactorLevel;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
