package com.yjxxt.wms.query;

public class AdminLoginQuery {
    private String amdinId;
    private String adminName;


    public String getAmdinId() {
        return amdinId;
    }

    public void setAmdinId(String amdinId) {
        this.amdinId = amdinId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "AdminLoginQuery{" +
                "amdinId='" + amdinId + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }
}
