package com.example.CRM.Domain;

import javax.persistence.*;

@Entity
@Table(name = "domain_list")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Code")
    private int code;
    @Column(name = "Name")
    private String name;
    @Column(name = "whiteBlackList")
    private int whiteBlackList =0;

    public Domain(){

    }

    public Domain(int code, String name, int whiteBlackList) {
        this.code = code;
        this.name = name;
        this.whiteBlackList = whiteBlackList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int domainCode) {
        this.code = domainCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWhiteBlackList() {
        return whiteBlackList;
    }

    public void setWhiteBlackList(int whiteBlackList) {
        this.whiteBlackList = whiteBlackList;
    }

    @Override
    public String toString() {
        return "DomainList{" +
                "domain_code=" + code +
                ", domain_name='" + name + '\'' +
                ", domain_WB=" + whiteBlackList +
                '}';
    }
}
