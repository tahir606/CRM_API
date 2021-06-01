package com.example.CRM.Product.Store;

import com.example.CRM.Event.Event;
import com.example.CRM.Module.ModuleLocking;
import com.example.CRM.Note.Note;
import com.example.CRM.Product.ProductModule;
import com.example.CRM.Task.Task;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import sun.security.pkcs11.Secmod;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "product_store")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PS_ID")
    private int psID;
    @Column(name = "PS_Name")
    private String name;
    @Column(name = "PS_Price")
    private int price;
    @Column(name = "PS_Description")
    private String description;
    @Column(name = "PS_Status")
    private int status;
    @Column(name = "PS_Type")
    private int type;
    @Column(name = "PS_Started")
    private Timestamp started;
    @Column(name = "PS_Property")
    private int property;
    @Column(name = "CreatedOn")
    private Timestamp createdOn;

    @Column(name = "CreatedBy")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CreatedBy", insertable = false, updatable = false)
    private Users users;


    @JsonManagedReference(value = "pdNoteList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productNoteList")
    private List<Note> pdNoteList;

    @JsonManagedReference(value = "pdProductModule")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productModuleList")
    private List<ProductModule> pdProductModule;

    @JsonManagedReference(value = "pdTaskList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productTaskList")
    private List<Task> pdTaskList;

    @JsonManagedReference(value = "pdEventList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productEventList")
    private List<Event> pdEventList;

    public Product(){

    }

//    public List<ModuleLocking> getPdModuleLockingList() {
//        return pdModuleLockingList;
//    }
//
//    public void setPdModuleLockingList(List<ModuleLocking> pdModuleLockingList) {
//        this.pdModuleLockingList = pdModuleLockingList;
//    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Note> getPdNoteList() {
        return pdNoteList;
    }

    public void setPdNoteList(List<Note> pdNoteList) {
        this.pdNoteList = pdNoteList;
    }

    public List<Task> getPdTaskList() {
        return pdTaskList;
    }

    public void setPdTaskList(List<Task> pdTaskList) {
        this.pdTaskList = pdTaskList;
    }

    public List<Event> getPdEventList() {
        return pdEventList;
    }

    public void setPdEventList(List<Event> pdEventList) {
        this.pdEventList = pdEventList;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getStarted() {
        return started;
    }

    public void setStarted(Timestamp started) {
        this.started = started;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<ProductModule> getPdProductModule() {
        return pdProductModule;
    }

    public void setPdProductModule(List<ProductModule> pdProductModule) {
        this.pdProductModule = pdProductModule;
    }

    @Override
    public String toString() {
        return "Product{" +
                "psID=" + psID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", started=" + started +
                ", property=" + property +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", pdNoteList=" + pdNoteList +
                ", pdTaskList=" + pdTaskList +
                ", pdEventList=" + pdEventList +
                '}';
    }
}
