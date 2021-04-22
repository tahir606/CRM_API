package com.example.CRM.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "users")
@Table()
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UCODE")
    private int userCode;
    @Column(name = "FNAME")
    private String fullName;
    @Column(name = "UNAME")
    private String userName;
    @Column(name = "Email")
    private String email;
    @Column(name = "UPASS")
    private String password;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "URIGHT")
    private String userRight;
    @Column(name = "FREZE")
    private int freeze = 0;
    @Column(name = "ISLOG")
    private int isLog;
    @Column(name = "ISEMAIL")
    private int isEmail;
    @Column(name = "availableCount")
    private Long availableCount;
    @Column(name = "availableString")
    private String availableString;

    public Users() {
    }

    public Users(String fullName,String time) {
        this.fullName = fullName;
        this.availableString = time;

    }

    public Users(String fullName, String userName, String email, String password, String note, String userRight) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.note = note;
        this.userRight = userRight;

    }

    public Users(int userCode, String fullName, String userName, Long availableCount) {
        this.userCode = userCode;
        this.fullName = fullName;
        this.userName = userName;
        this.availableCount = availableCount;
    }

    public String getAvailableString() {
        return availableString;
    }

    public void setAvailableString(String availableString) {
        this.availableString = availableString;
    }

    public Long getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Long availableCount) {
        this.availableCount = availableCount;
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

    @JsonIgnore
    @JsonProperty(value = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public int getIsLog() {
        return isLog;
    }

    public void setIsLog(int isLog) {
        this.isLog = isLog;
    }

    public int getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(int isEmail) {
        this.isEmail = isEmail;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userCode=" + userCode +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", note='" + note + '\'' +
                ", userRight='" + userRight + '\'' +
                ", freeze=" + freeze +
                ", isLog=" + isLog +
                ", isEmail=" + isEmail +
                ", availableCount=" + availableCount +
                ", availableString='" + availableString + '\'' +
                '}';
    }
}
