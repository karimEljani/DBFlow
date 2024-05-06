package com.ibizabroker.lms.entity;

import lombok.Getter;

@Getter
public class JwtRequest {

    private String username;
    private String password;

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setUserPassword(String userPassword) {
        this.password = userPassword;
    }
}
