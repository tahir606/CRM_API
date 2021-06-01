package com.example.CRM.Module;

import com.example.CRM.LeadStore.Lead;
import com.example.CRM.Product.ProductModule;
import com.example.CRM.Product.Store.Product;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "module_locking")
@Table
public class ModuleLocking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Module_ID")
    private int moduleId;

    @Column(name = "CreatedBy")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CreatedBy", insertable = false, updatable = false)
    private Users users;

    @Column(name = "State")
    private int state = 0;
    @Column(name = "Locked_Time")
    private Timestamp lockedTime;
    @Column(name = "UnLocked_Time")
    private Timestamp unLockedTime;
    @Column(name = "Description")
    private String description;

    @Column(name = "PM_ID")
    private Integer pmID;
    @JsonBackReference(value = "pmModuleLockingList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PM_ID", insertable = false, updatable = false)
    private ProductModule productMModuleLockingList;

    public ModuleLocking() {

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ProductModule getProductMModuleLockingList() {
        return productMModuleLockingList;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setProductMModuleLockingList(ProductModule productMModuleLockingList) {
        this.productMModuleLockingList = productMModuleLockingList;
    }

    public Integer getPmID() {
        return pmID;
    }

    public void setPmID(Integer pmID) {
        this.pmID = pmID;
    }

//    public Integer getPsID() {
//        return psID;
//    }
//
//    public void setPsID(Integer psID) {
//        this.psID = psID;
//    }
//
//    public Product getProductModuleLockingList() {
//        return productModuleLockingList;
//    }
//
//    public void setProductModuleLockingList(Product productModuleLockingList) {
//        this.productModuleLockingList = productModuleLockingList;
//    }


    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }


    public Timestamp getUnLockedTime() {
        return unLockedTime;
    }

    public void setUnLockedTime(Timestamp unLockedTime) {
        this.unLockedTime = unLockedTime;
    }

    public Timestamp getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Timestamp lockedTime) {
        this.lockedTime = lockedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ModuleLocking{" +
                "moduleId=" + moduleId +
                ", locked_Time=" + lockedTime +
                ", unLocked_Time=" + unLockedTime +
                ", description='" + description + '\'' +

                '}';
    }
}
