package com.yjxxt.wms.query;

import com.yjxxt.wms.base.BaseQuery;

public class AdminQuery extends BaseQuery {
    private String adminAccount;
    private String adminEmail;
    private String adminPhone;

    public AdminQuery() {

    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    @Override
    public String toString() {
        return "AdminQuery{" +
                "adminAccount='" + adminAccount + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                "} " + super.toString();
    }
}
