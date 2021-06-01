package com.example.CRM.Product;

import com.example.CRM.Contact.Contact;
import com.example.CRM.Module.ModuleLocking;
import com.example.CRM.Product.Store.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "product_module")
@Table
public class ProductModule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PM_ID")
    private int pmID;
    @Column(name = "PM_Name")
    private String name;
    @Column(name = "PM_Description")
    private String description;

    @Column(name = "CreatedBy")
    private int createdBy;

    @Column(name = "CreatedOn")
    private Timestamp createdOn;

    @Column(name = "PS_ID")
    private Integer psID;
    @JsonBackReference(value = "pdProductModule")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PS_ID", insertable = false, updatable = false)
    private Product productModuleList;

    @JsonManagedReference(value = "pmModuleLockingList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productMModuleLockingList")
    private List<ModuleLocking> pmModuleLockingList;

    public ProductModule(){

    }

    public List<ModuleLocking> getPmModuleLockingList() {
        return pmModuleLockingList;
    }

    public void setPmModuleLockingList(List<ModuleLocking> pmModuleLockingList) {
        this.pmModuleLockingList = pmModuleLockingList;
    }

    public int getPmID() {
        return pmID;
    }

    public void setPmID(int pmID) {
        this.pmID = pmID;
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

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getPsID() {
        return psID;
    }

    public void setPsID(Integer psID) {
        this.psID = psID;
    }

    public Product getProductModuleList() {
        return productModuleList;
    }

    public void setProductModuleList(Product productModuleList) {
        this.productModuleList = productModuleList;
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
