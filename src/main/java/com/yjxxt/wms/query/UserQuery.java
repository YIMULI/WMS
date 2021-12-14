package com.yjxxt.wms.query;

import com.yjxxt.wms.base.BaseQuery;

public class UserQuery extends BaseQuery {
    private String userIdStr;
    private String userName;
    private String userAccount;
    private String userId;
    private String email;
    private String phone;


    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "userIdStr='" + userIdStr + '\'' +
                ", userName='" + userName + '\'' +
                "} " + super.toString();
    }
}
