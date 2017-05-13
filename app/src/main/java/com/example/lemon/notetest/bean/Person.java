package com.example.lemon.notetest.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Lemon on 2017/5/12.
 */

public class Person extends BmobObject {
    private String userName;
    private String passWord;
    private String PhoneNum;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
