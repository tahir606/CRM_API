package com.example.CRM.Contact;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "contact_store")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CS_ID")
    private int contactID;
    @Column(name = "CS_FirstName")
    private String firstName;
    @Column(name = "CS_LastName")
    private String lastName;
    @Column(name = "CS_DOB")
    private Date dateOfBirth;
    @Column(name = "CS_Address")
    private String address;
    @Column(name = "CS_City")
    private String city;
    @Column(name = "CS_Country")
    private String country;
    @Column(name = "CS_Note")
    private String note;
    @Column(name = "CreatedOn")
    private Date createdOn;
    @Column(name = "Freeze")
    private int freeze;
    @Column(name = "CL_ID")
    private int clientID;
    @Column(name = "Created_By")
    private int createdBy;

    public Contact(){

    }
    public Contact(int contactID, String firstName, String lastName, Date dateOfBirth, String address, String city, String country,
                   String note, Date createdOn, int freeze, int clientID, int createdBy) {
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.country = country;
        this.note = note;
        this.createdOn = createdOn;
        this.freeze = freeze;
        this.clientID = clientID;
        this.createdBy = createdBy;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Contact_Store{" +
                "contact_ID=" + contactID +
                ", cs_FirstName='" + firstName + '\'' +
                ", cs_LastName='" + lastName + '\'' +
                ", cs_DateOfBirth=" + dateOfBirth +
                ", cs_Address='" + address + '\'' +
                ", cs_City='" + city + '\'' +
                ", cs_Country='" + country + '\'' +
                ", cs_Note='" + note + '\'' +
                ", createdDone=" + createdOn +
                ", freeze=" + freeze +
                ", client_ID=" + clientID +
                ", created_By=" + createdBy +
                '}';
    }
}
