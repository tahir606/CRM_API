package com.example.CRM.Client;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "client_store")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Cl_ID")
    private int clientID;
    @Column(name = "Cl_Name")
    private String name;
    @Column(name = "Cl_Owner")
    private String owner;
    @Column(name = "Cl_Email")
    private String email;
    @Column(name = "Cl_Phone")
    private String phoneNo;
    @Column(name = "Cl_Address")
    private String address;
    @Column(name = "Cl_City")
    private String city;
    @Column(name = "Cl_Country")
    private String country;
    @Column(name = "Cl_Website")
    private String website;
    @Column(name = "Cl_JoinDate")
    private Date joinDate;
    @Column(name = "Cl_Bcycle")
    private Date bicycle;
    @Column(name = "Cl_Type")
    private int type;
    @Column(name = "From_Lead")
    private int fromLead;
    @Column(name = "Created_By")
    private int createdBy;
    @Column(name = "Created_On")
    private Date createdOn;

    public Client(){

    }
    public Client(int clientID, String name, String owner, String email, String phoneNo,
                  String address, String city, String country, String website,
                  Date joinDate, Date bicycle, int type, int fromLead, int createdBy, Date createdOn) {
        this.clientID = clientID;
        this.name = name;
        this.owner = owner;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.city = city;
        this.country = country;
        this.website = website;
        this.joinDate = joinDate;
        this.bicycle = bicycle;
        this.type = type;
        this.fromLead = fromLead;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getBicycle() {
        return bicycle;
    }

    public void setBicycle(Date bicycle) {
        this.bicycle = bicycle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFromLead() {
        return fromLead;
    }

    public void setFromLead(int fromLead) {
        this.fromLead = fromLead;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Client_Store{" +
                "client_ID=" + clientID +
                ", client_Name='" + name + '\'' +
                ", client_Owner='" + owner + '\'' +
                ", client_Email='" + email + '\'' +
                ", client_Phone='" + phoneNo + '\'' +
                ", client_Address='" + address + '\'' +
                ", client_City='" + city + '\'' +
                ", client_Country='" + country + '\'' +
                ", client_Website='" + website + '\'' +
                ", client_JoinDate=" + joinDate +
                ", client_Bcycle=" + bicycle +
                ", client_Type=" + type +
                ", from_Lead=" + fromLead +
                ", created_By=" + createdBy +
                ", create_Don=" + createdOn +
                '}';
    }
}
