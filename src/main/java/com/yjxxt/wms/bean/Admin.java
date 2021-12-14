package com.yjxxt.wms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Admin extends User {
    private Integer adminId;

    private String adminAccount;

    private String adminPassword;

    private String adminName;

    private String adminSex;

    private String adminAge;

    private String adminPhone;

    private String adminAddress;

    private String adminEmail;

    private Integer isValid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount == null ? null : adminAccount.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex == null ? null : adminSex.trim();
    }

    public String getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(String adminAge) {
        this.adminAge = adminAge == null ? null : adminAge.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress == null ? null : adminAddress.trim();
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail == null ? null : adminEmail.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminAccount='" + adminAccount + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminSex='" + adminSex + '\'' +
                ", adminAge='" + adminAge + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", adminAddress='" + adminAddress + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}