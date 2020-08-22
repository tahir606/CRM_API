package com.example.CRM.User;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UCODE")
    private int userCode;
    @Column(name = "FNAME")
    private String fullName;
    @Column(name = "UNAME")
    private String userName;
    @Column(name = "Email")
    private String email;
    @Column(name = "UPASS")
    private String userPassword;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "URIGHT")
    private String userRight;
    @Column(name = "FREZE")
    private boolean freeze = false;
    @Column(name = "ISLOG")
    private int isLog;
    @Column(name = "ISEMAIL")
    private boolean isEmail;

    public Users() {
    }

    public Users(String fullName, String userName, String email, String userPassword, String note, String userRight) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.note = note;
        this.userRight = userRight;

    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUserRight() {
        return userRight;
    }

    public void setUserRight(String userRight) {
        this.userRight = userRight;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    public int getIsLog() {
        return isLog;
    }

    public void setIsLog(int isLog) {
        this.isLog = isLog;
    }

//    public boolean isEmail() {
//        return isEmail;
//    }
//
//    public void setEmail(boolean email) {
//        isEmail = email;
//    }

    @Override
    public String toString() {
        return "Users{" +
                "userCode=" + userCode +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", note='" + note + '\'' +
                ", userRight='" + userRight + '\'' +
                ", freeze=" + freeze +
                ", isLog=" + isLog +
//                ", isEmail=" + isEmail +
                '}';
    }
}
