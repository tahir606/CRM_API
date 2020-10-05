package com.example.CRM.Domain;

import javax.persistence.*;

@Entity
@Table(name = "domain_list")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "domain_code")
    private int domainCode;
    @Column(name = "domain_name")
    private String name;
    @Column(name = "domain_WB")
    private int whiteBlackList;

    public Domain(){

    }

    public Domain(int domainCode, String name, int whiteBlackList) {
        this.domainCode = domainCode;
        this.name = name;
        this.whiteBlackList = whiteBlackList;
    }

    public int getDomainCode() {
        return domainCode;
    }

    public void setDomainCode(int domainCode) {
        this.domainCode = domainCode;
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
                "domain_code=" + domainCode +
                ", domain_name='" + name + '\'' +
                ", domain_WB=" + whiteBlackList +
                '}';
    }
}
