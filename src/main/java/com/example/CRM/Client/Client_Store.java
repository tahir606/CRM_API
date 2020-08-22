package com.example.CRM.Client;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "client_store")
public class Client_Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Cl_ID")
    private int client_ID;
    @Column(name = "Cl_Name")
    private String client_Name;
    @Column(name = "Cl_Owner")
    private String client_Owner;
    @Column(name = "Cl_Email")
    private String client_Email;
    @Column(name = "Cl_Phone")
    private String client_Phone;
    @Column(name = "Cl_Address")
    private String client_Address;
    @Column(name = "Cl_City")
    private String client_City;
    @Column(name = "Cl_Country")
    private String client_Country;
    @Column(name = "Cl_Website")
    private String client_Website;
    @Column(name = "Cl_JoinDate")
    private Date client_JoinDate;
    @Column(name = "Cl_Bcycle")
    private Date client_Bcycle;
    @Column(name = "Cl_Type")
    private int client_Type;
    @Column(name = "From_Lead")
    private int from_Lead;
    @Column(name = "Created_By")
    private int created_By;
    @Column(name = "Create_Don")
    private Date create_Don;

    public Client_Store(){

    }
    public Client_Store(int client_ID, String client_Name, String client_Owner, String client_Email, String client_Phone,
                        String client_Address, String client_City, String client_Country, String client_Website,
                        Date client_JoinDate, Date client_Bcycle, int client_Type, int from_Lead, int created_By, Date create_Don) {
        this.client_ID = client_ID;
        this.client_Name = client_Name;
        this.client_Owner = client_Owner;
        this.client_Email = client_Email;
        this.client_Phone = client_Phone;
        this.client_Address = client_Address;
        this.client_City = client_City;
        this.client_Country = client_Country;
        this.client_Website = client_Website;
        this.client_JoinDate = client_JoinDate;
        this.client_Bcycle = client_Bcycle;
        this.client_Type = client_Type;
        this.from_Lead = from_Lead;
        this.created_By = created_By;
        this.create_Don = create_Don;
    }

    public int getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    public String getClient_Name() {
        return client_Name;
    }

    public void setClient_Name(String client_Name) {
        this.client_Name = client_Name;
    }

    public String getClient_Owner() {
        return client_Owner;
    }

    public void setClient_Owner(String client_Owner) {
        this.client_Owner = client_Owner;
    }

    public String getClient_Email() {
        return client_Email;
    }

    public void setClient_Email(String client_Email) {
        this.client_Email = client_Email;
    }

    public String getClient_Phone() {
        return client_Phone;
    }

    public void setClient_Phone(String client_Phone) {
        this.client_Phone = client_Phone;
    }

    public String getClient_Address() {
        return client_Address;
    }

    public void setClient_Address(String client_Address) {
        this.client_Address = client_Address;
    }

    public String getClient_City() {
        return client_City;
    }

    public void setClient_City(String client_City) {
        this.client_City = client_City;
    }

    public String getClient_Country() {
        return client_Country;
    }

    public void setClient_Country(String client_Country) {
        this.client_Country = client_Country;
    }

    public String getClient_Website() {
        return client_Website;
    }

    public void setClient_Website(String client_Website) {
        this.client_Website = client_Website;
    }

    public Date getClient_JoinDate() {
        return client_JoinDate;
    }

    public void setClient_JoinDate(Date client_JoinDate) {
        this.client_JoinDate = client_JoinDate;
    }

    public Date getClient_Bcycle() {
        return client_Bcycle;
    }

    public void setClient_Bcycle(Date client_Bcycle) {
        this.client_Bcycle = client_Bcycle;
    }

    public int getClient_Type() {
        return client_Type;
    }

    public void setClient_Type(int client_Type) {
        this.client_Type = client_Type;
    }

    public int getFrom_Lead() {
        return from_Lead;
    }

    public void setFrom_Lead(int from_Lead) {
        this.from_Lead = from_Lead;
    }

    public int getCreated_By() {
        return created_By;
    }

    public void setCreated_By(int created_By) {
        this.created_By = created_By;
    }

    public Date getCreate_Don() {
        return create_Don;
    }

    public void setCreate_Don(Date create_Don) {
        this.create_Don = create_Don;
    }

    @Override
    public String toString() {
        return "Client_Store{" +
                "client_ID=" + client_ID +
                ", client_Name='" + client_Name + '\'' +
                ", client_Owner='" + client_Owner + '\'' +
                ", client_Email='" + client_Email + '\'' +
                ", client_Phone='" + client_Phone + '\'' +
                ", client_Address='" + client_Address + '\'' +
                ", client_City='" + client_City + '\'' +
                ", client_Country='" + client_Country + '\'' +
                ", client_Website='" + client_Website + '\'' +
                ", client_JoinDate=" + client_JoinDate +
                ", client_Bcycle=" + client_Bcycle +
                ", client_Type=" + client_Type +
                ", from_Lead=" + from_Lead +
                ", created_By=" + created_By +
                ", create_Don=" + create_Don +
                '}';
    }
}
