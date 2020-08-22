package com.example.CRM.Domain;

import javax.persistence.*;

@Entity
@Table(name = "domain_list")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "domain_code")
    private int domain_code;
    @Column(name = "domain_name")
    private String domain_name;
    @Column(name = "domain_WB")
    private int domain_WB;

    public Domain(){

    }

    public Domain(int domain_code, String domain_name, int domain_WB) {
        this.domain_code = domain_code;
        this.domain_name = domain_name;
        this.domain_WB = domain_WB;
    }

    public int getDomain_code() {
        return domain_code;
    }

    public void setDomain_code(int domain_code) {
        this.domain_code = domain_code;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    public int getDomain_WB() {
        return domain_WB;
    }

    public void setDomain_WB(int domain_WB) {
        this.domain_WB = domain_WB;
    }

    @Override
    public String toString() {
        return "DomainList{" +
                "domain_code=" + domain_code +
                ", domain_name='" + domain_name + '\'' +
                ", domain_WB=" + domain_WB +
                '}';
    }
}
