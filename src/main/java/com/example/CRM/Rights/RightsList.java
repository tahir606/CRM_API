package com.example.CRM.Rights;

import javax.persistence.*;

@Entity
@Table(name = "rights_list")
public class RightsList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Rights_Code")
    private int rightsCode;
    @Column(name = "Rights_Name")
    private String name;
    @Column(name = "Rights_Freeze")
    private int freeze;

    public RightsList(){

    }
    public RightsList(int rightsCode, String name, int freeze) {
        this.rightsCode = rightsCode;
        this.name = name;
        this.freeze = freeze;
    }

    public int getRightsCode() {
        return rightsCode;
    }

    public void setRightsCode(int rightsCode) {
        this.rightsCode = rightsCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "RightsList{" +
                "rights_Code=" + rightsCode +
                ", rights_Name='" + name + '\'' +
                ", rights_freeze='" + freeze + '\'' +
                '}';
    }
}
