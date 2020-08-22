package com.example.CRM.Client.ClientType;

import javax.persistence.*;

@Entity
@Table(name = "client_Type")
public class ClientType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ct_Code")
    private int ct_Code;
    @Column(name = "ct_Name")
    private String ct_Name;

    public ClientType(){

    }
    public ClientType(int ct_Code, String ct_Name) {
        this.ct_Code = ct_Code;
        this.ct_Name = ct_Name;
    }

    public int getCt_Code() {
        return ct_Code;
    }

    public void setCt_Code(int ct_Code) {
        this.ct_Code = ct_Code;
    }

    public String getCt_Name() {
        return ct_Name;
    }

    public void setCt_Name(String ct_Name) {
        this.ct_Name = ct_Name;
    }

    @Override
    public String toString() {
        return "ClientType{" +
                "ct_Code=" + ct_Code +
                ", ct_Name='" + ct_Name + '\'' +
                '}';
    }
}
