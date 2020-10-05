package com.example.CRM.Client.ClientType;

import javax.persistence.*;

@Entity
@Table(name = "client_Type")
public class ClientType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ct_Code")
    private int ctCode;
    @Column(name = "ct_Name")
    private String name;

    public ClientType(){

    }
    public ClientType(int ctCode, String name) {
        this.ctCode = ctCode;
        this.name = name;
    }

    public int getCtCode() {
        return ctCode;
    }

    public void setCtCode(int ctCode) {
        this.ctCode = ctCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClientType{" +
                "ct_Code=" + ctCode +
                ", ct_Name='" + name + '\'' +
                '}';
    }
}
