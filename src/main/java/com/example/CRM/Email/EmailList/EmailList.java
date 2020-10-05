package com.example.CRM.Email.EmailList;

import javax.persistence.*;

@Entity
@Table(name = "email_list")
public class EmailList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Email_ID")
    private int emailID;
    @Column(name = "Email_Name")
    private String name;
    @Column(name = "CL_ID")
    private int clientID;
    @Column(name = "User_Code")
    private int userCode;
    @Column(name = "CS_ID")
    private int contactID;
    @Column(name = "LS_ID")
    private int leadsID;

    public EmailList(){

    }
    public EmailList(int emailID, String name, int clientID, int userCode, int contactID, int leadsID) {
        this.emailID = emailID;
        this.name = name;
        this.clientID = clientID;
        this.userCode = userCode;
        this.contactID = contactID;
        this.leadsID = leadsID;
    }

    public int getEmailID() {
        return emailID;
    }

    public void setEmailID(int emailID) {
        this.emailID = emailID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getLeadsID() {
        return leadsID;
    }

    public void setLeadsID(int leadsID) {
        this.leadsID = leadsID;
    }

    @Override
    public String toString() {
        return "EmailList{" +
                "email_ID=" + emailID +
                ", email_Name='" + name + '\'' +
                ", client_ID=" + clientID +
                ", userCode=" + userCode +
                ", contact_ID=" + contactID +
                ", leads_ID=" + leadsID +
                '}';
    }
}
