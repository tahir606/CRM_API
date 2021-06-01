package com.example.CRM.Phone;

import com.example.CRM.Client.Client;
import com.example.CRM.Contact.Contact;
import com.example.CRM.LeadStore.Lead;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "phone_list")
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "phoneID",
//        scope     = Integer.class)
public class PhoneList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Phone_ID")
    private int phoneID;

    @Column(name = "Phone_Number")
    private String number;

    @Column(name = "User_CODE")
    private Integer userCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_Code", insertable = false, updatable = false)
    private Users users;

    @Column(name = "Leads_ID")
    private Integer leadsId;
    @JsonBackReference(value = "ldPhoneLists")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Leads_ID", insertable = false, updatable = false)
    private Lead leadsPhoneList;


    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clPhoneLists")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client clientPhoneList;

    @Column(name = "CS_ID")
    private Integer contactID;
    @JsonBackReference(value = "coPhoneLists")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CS_ID", insertable = false, updatable = false)
    private Contact contactPhoneList;


    public PhoneList() {

    }

    public Integer getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(Integer leadsId) {
        this.leadsId = leadsId;
    }

    public Lead getLeadsPhoneList() {
        return leadsPhoneList;
    }

    public void setLeadsPhoneList(Lead leadsPhoneList) {
        this.leadsPhoneList = leadsPhoneList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Contact getContactPhoneList() {
        return contactPhoneList;
    }

    public void setContactPhoneList(Contact contact) {
        this.contactPhoneList = contact;
    }

    public Client getClientPhoneList() {
        return clientPhoneList;
    }

    public void setClientPhoneList(Client client) {
        this.clientPhoneList = client;
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

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    @Override
    public String toString() {
        return "PhoneList{" +
                "phoneID=" + phoneID +
                ", number='" + number + '\'' +
                ", userCode=" + userCode +
                ", users=" + users +
                ", leadsId=" + leadsId +
                ", leadsPhoneList=" + leadsPhoneList +
                ", clientID=" + clientID +
                ", clientPhoneList=" + clientPhoneList +
                ", contactID=" + contactID +
                ", contactPhoneList=" + contactPhoneList +
                '}';
    }
}
