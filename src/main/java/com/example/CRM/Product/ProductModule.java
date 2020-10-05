package com.example.CRM.Product;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "product_module")
public class ProductModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PM_ID")
    private int pmID;
    @Column(name = "PS_ID")
    private int psID;
    @Column(name = "PM_Name")
    private String name;
    @Column(name = "PM_Description")
    private String description;
    @Column(name = "CreatedBy")
    private int createdBy;
    @Column(name = "CreatedOn")
    private Date createdOn;

    public ProductModule(){

    }
    public ProductModule(int pmID, int psID, String name, String description, int createdBy, Date createdOn) {
        this.pmID = pmID;
        this.psID = psID;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public int getPmID() {
        return pmID;
    }

    public void setPmID(int pmID) {
        this.pmID = pmID;
    }

    public int getPsID() {
        return psID;
    }

    public void setPsID(int psID) {
        this.psID = psID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "ProductModule{" +
                "pm_ID=" + pmID +
                ", ps_ID=" + psID +
                ", pm_Name='" + name + '\'' +
                ", pm_Description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                '}';
    }
}
