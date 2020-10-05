package com.example.CRM.Email.EmailRelation;

import javax.persistence.*;

@Entity
@Table(name = "email_relation")
public class EmailRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email_No")
    private int emailNo;
    @Column(name = "email_Type")
    private int type;
    @Column(name = "email_ID")
    private int emailID;
    @Column(name = "CL_ID")
    private int clID;
    @Column(name = "user_Code")
    private int userCode;
    @Column(name = "CS_ID")
    private int csID;

    public EmailRelation() {

    }

    public EmailRelation(int emailNo, int type, int emailID, int clID, int userCode, int csID) {
        this.emailNo = emailNo;
        this.type = type;
        this.emailID = emailID;
        this.clID = clID;
        this.userCode = userCode;
        this.csID = csID;
    }

    public int getEmailNo() {
        return emailNo;
    }

    public void setEmailNo(int emailNo) {
        this.emailNo = emailNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEmailID() {
        return emailID;
    }

    public void setEmailID(int emailID) {
        this.emailID = emailID;
    }

    public int getClID() {
        return clID;
    }

    public void setClID(int clID) {
        this.clID = clID;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getCsID() {
        return csID;
    }

    public void setCsID(int csID) {
        this.csID = csID;
    }

    @Override
    public String toString() {
        return "EmailRelation{" +
                "emailNo=" + emailNo +
                ", email_Type=" + type +
                ", email_ID=" + emailID +
                ", cl_ID=" + clID +
                ", user_Code=" + userCode +
                ", cs_ID=" + csID +
                '}';
    }
}
