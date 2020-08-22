package com.example.CRM.Email.EmailList;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name = "email_list")
public class EmailList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Email_ID")
    private int email_ID;
    @Column(name = "Email_Name")
    private String email_Name;
    @Column(name = "CL_ID")
    private int client_ID;
    @Column(name = "User_Code")
    private int userCode;
    @Column(name = "CS_ID")
    private int contact_ID;
    @Column(name = "LS_ID")
    private int leads_ID;

    public EmailList(){

    }
    public EmailList(int email_ID, String email_Name, int client_ID, int userCode, int contact_ID, int leads_ID) {
        this.email_ID = email_ID;
        this.email_Name = email_Name;
        this.client_ID = client_ID;
        this.userCode = userCode;
        this.contact_ID = contact_ID;
        this.leads_ID = leads_ID;
    }

    public int getEmail_ID() {
        return email_ID;
    }

    public void setEmail_ID(int email_ID) {
        this.email_ID = email_ID;
    }

    public String getEmail_Name() {
        return email_Name;
    }

    public void setEmail_Name(String email_Name) {
        this.email_Name = email_Name;
    }

    public int getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getContact_ID() {
        return contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    public int getLeads_ID() {
        return leads_ID;
    }

    public void setLeads_ID(int leads_ID) {
        this.leads_ID = leads_ID;
    }

    @Override
    public String toString() {
        return "EmailList{" +
                "email_ID=" + email_ID +
                ", email_Name='" + email_Name + '\'' +
                ", client_ID=" + client_ID +
                ", userCode=" + userCode +
                ", contact_ID=" + contact_ID +
                ", leads_ID=" + leads_ID +
                '}';
    }
}
