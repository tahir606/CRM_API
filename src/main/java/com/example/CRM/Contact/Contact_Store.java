package com.example.CRM.Contact;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "contact_store")
public class Contact_Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CS_ID")
    private int contact_ID;
    @Column(name = "CS_FirstName")
    private String cs_FirstName;
    @Column(name = "CS_LastName")
    private String cs_LastName;
    @Column(name = "CS_DOB")
    private Date cs_DateOfBirth;
    @Column(name = "CS_Address")
    private String cs_Address;

    @Column(name = "CS_City")
    private String cs_City;
    @Column(name = "CS_Country")
    private String cs_Country;
    @Column(name = "CS_Note")
    private String cs_Note;
    @Column(name = "CreatedDone")
    private Date createdDone;
    @Column(name = "Freeze")
    private int freeze;
    @Column(name = "CL_ID")
    private int client_ID;
    @Column(name = "Created_By")
    private int created_By;

    public Contact_Store(){

    }
    public Contact_Store(int contact_ID, String cs_FirstName, String cs_LastName, Date cs_DateOfBirth,String cs_Address, String cs_City, String cs_Country,
                         String cs_Note, Date createdDone, int freeze, int client_ID, int created_By) {
        this.contact_ID = contact_ID;
        this.cs_FirstName = cs_FirstName;
        this.cs_LastName = cs_LastName;
        this.cs_DateOfBirth = cs_DateOfBirth;
        this.cs_Address=cs_Address;
        this.cs_City = cs_City;
        this.cs_Country = cs_Country;
        this.cs_Note = cs_Note;
        this.createdDone = createdDone;
        this.freeze = freeze;
        this.client_ID = client_ID;
        this.created_By = created_By;
    }

    public int getContact_ID() {
        return contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    public String getCs_FirstName() {
        return cs_FirstName;
    }

    public void setCs_FirstName(String cs_FirstName) {
        this.cs_FirstName = cs_FirstName;
    }

    public String getCs_LastName() {
        return cs_LastName;
    }

    public void setCs_LastName(String cs_LastName) {
        this.cs_LastName = cs_LastName;
    }

    public Date getCs_DateOfBirth() {
        return cs_DateOfBirth;
    }

    public void setCs_DateOfBirth(Date cs_DateOfBirth) {
        this.cs_DateOfBirth = cs_DateOfBirth;
    }

    public String getCs_Address() {
        return cs_Address;
    }

    public void setCs_Address(String cs_Address) {
        this.cs_Address = cs_Address;
    }


    public String getCs_City() {
        return cs_City;
    }

    public void setCs_City(String cs_City) {
        this.cs_City = cs_City;
    }

    public String getCs_Country() {
        return cs_Country;
    }

    public void setCs_Country(String cs_Country) {
        this.cs_Country = cs_Country;
    }

    public String getCs_Note() {
        return cs_Note;
    }

    public void setCs_Note(String cs_Note) {
        this.cs_Note = cs_Note;
    }

    public Date getCreatedDone() {
        return createdDone;
    }

    public void setCreatedDone(Date createdDone) {
        this.createdDone = createdDone;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public int getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    public int getCreated_By() {
        return created_By;
    }

    public void setCreated_By(int created_By) {
        this.created_By = created_By;
    }

    @Override
    public String toString() {
        return "Contact_Store{" +
                "contact_ID=" + contact_ID +
                ", cs_FirstName='" + cs_FirstName + '\'' +
                ", cs_LastName='" + cs_LastName + '\'' +
                ", cs_DateOfBirth=" + cs_DateOfBirth +
                ", cs_Address='" + cs_Address + '\'' +
                ", cs_City='" + cs_City + '\'' +
                ", cs_Country='" + cs_Country + '\'' +
                ", cs_Note='" + cs_Note + '\'' +
                ", createdDone=" + createdDone +
                ", freeze=" + freeze +
                ", client_ID=" + client_ID +
                ", created_By=" + created_By +
                '}';
    }
}
