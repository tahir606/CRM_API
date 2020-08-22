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
    private int email_Type;
    @Column(name = "email_ID")
    private int email_ID;
    @Column(name = "CL_ID")
    private int cl_ID;
    @Column(name = "user_Code")
    private int user_Code;
    @Column(name = "CS_ID")
    private int cs_ID;

    public EmailRelation() {

    }

    public EmailRelation(int emailNo, int email_Type, int email_ID, int cl_ID, int user_Code, int cs_ID) {
        this.emailNo = emailNo;
        this.email_Type = email_Type;
        this.email_ID = email_ID;
        this.cl_ID = cl_ID;
        this.user_Code = user_Code;
        this.cs_ID = cs_ID;
    }

    public int getEmailNo() {
        return emailNo;
    }

    public void setEmailNo(int emailNo) {
        this.emailNo = emailNo;
    }

    public int getEmail_Type() {
        return email_Type;
    }

    public void setEmail_Type(int email_Type) {
        this.email_Type = email_Type;
    }

    public int getEmail_ID() {
        return email_ID;
    }

    public void setEmail_ID(int email_ID) {
        this.email_ID = email_ID;
    }

    public int getCl_ID() {
        return cl_ID;
    }

    public void setCl_ID(int cl_ID) {
        this.cl_ID = cl_ID;
    }

    public int getUser_Code() {
        return user_Code;
    }

    public void setUser_Code(int user_Code) {
        this.user_Code = user_Code;
    }

    public int getCs_ID() {
        return cs_ID;
    }

    public void setCs_ID(int cs_ID) {
        this.cs_ID = cs_ID;
    }

    @Override
    public String toString() {
        return "EmailRelation{" +
                "emailNo=" + emailNo +
                ", email_Type=" + email_Type +
                ", email_ID=" + email_ID +
                ", cl_ID=" + cl_ID +
                ", user_Code=" + user_Code +
                ", cs_ID=" + cs_ID +
                '}';
    }
}
