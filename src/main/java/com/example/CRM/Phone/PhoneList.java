package com.example.CRM.Phone;

import javax.persistence.*;

@Entity
@Table(name = "phone_list")
public class PhoneList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Phone_ID")
    private int phoneID;
    @Column(name = "Phone_Number")
    private String number;
    @Column(name = "Client_ID")
    private int clientID;
    @Column(name = "User_CODE")
    private int userCode;
    @Column(name = "Contact_ID")
    private int contactID;
    @Column(name = "Leads_ID")
    private int leadsId;

    public PhoneList(){

    }
    public PhoneList(int phoneID, String number, int clientID, int userCode, int contactID, int leadsId) {
        this.phoneID = phoneID;
        this.number = number;
        this.clientID = clientID;
        this.userCode = userCode;
        this.contactID = contactID;
        this.leadsId = leadsId;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public int getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(int leadsId) {
        this.leadsId = leadsId;
    }

    @Override
    public String toString() {
        return "PhoneList{" +
                "phone_ID=" + phoneID +
                ", phone_Number='" + number + '\'' +
                ", client_ID=" + clientID +
                ", userCode=" + userCode +
                ", contact_ID=" + contactID +
                ", leads_id=" + leadsId +
                '}';
    }
}
