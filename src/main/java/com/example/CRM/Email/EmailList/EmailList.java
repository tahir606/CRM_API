package com.example.CRM.Email.EmailList;

import com.example.CRM.Client.Client;
import com.example.CRM.Contact.Contact;
import com.example.CRM.LeadStore.Lead;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "email_list")
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "emailID",
//        scope     = Integer.class)
public class EmailList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Email_ID")
    private int emailID;
    @Column(name = "Email_Name")
    private String name;
    @Column(name = "Email_Address", unique = true)
    private String address;


    @Column(name = "User_Code")
    private Integer userCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_Code", insertable = false, updatable = false)
    private Users users;

    @Column(name = "LS_ID")
    private Integer leadsID;
    @JsonBackReference(value = "ldEmailLists")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LS_ID", insertable = false, updatable = false)
    private Lead leadsEmailList;

    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clEmailLists")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client clientEmailList;

    @Column(name = "CS_ID")
    private Integer contactID;
    @JsonBackReference(value = "coEmailLists")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CS_ID", insertable = false, updatable = false)
    private Contact contactEmailList;


    public EmailList() {
    }

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public Integer getLeadsID() {
        return leadsID;
    }

    public void setLeadsID(Integer leadsID) {
        this.leadsID = leadsID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public EmailList(String address, String name) {
        this.address = address;
        this.name = name;
    }



    public Lead getLeadsEmailList() {
        return leadsEmailList;
    }

    public void setLeadsEmailList(Lead leadsEmailList) {
        this.leadsEmailList = leadsEmailList;
    }

    public Contact getContactEmailList() {
        return contactEmailList;
    }

    public void setContactEmailList(Contact contact) {
        this.contactEmailList = contact;
    }

    public Client getClientEmailList() {
        return clientEmailList;
    }

    public void setClientEmailList(Client client) {
        this.clientEmailList = client;
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



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmailList{" +
                "emailID=" + emailID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", userCode=" + userCode +
                ", users=" + users +
                ", leadsID=" + leadsID +
                ", leadsEmailList=" + leadsEmailList +
                ", clientID=" + clientID +
                ", clientEmailList=" + clientEmailList +
                ", contactID=" + contactID +
                ", contactEmailList=" + contactEmailList +
                '}';
    }
}
