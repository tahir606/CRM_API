package com.example.CRM.Product.Store;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "product_store")
public class Product {
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
    private Date started;
    @Column(name = "PS_Property")
    private int property;
    @Column(name = "CreatedBy")
    private int createdBy;
    @Column(name = "CreatedOn")
    private Date createdOn;
    public Product(){

    }
    public Product(int psID, String name, int price, String description, int status, int type, Date started, int property,
                   int createdBy, Date createdOn) {
        this.psID = psID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.type = type;
        this.started = started;
        this.property = property;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
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

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ps_ID=" + psID +
                ", ps_Name='" + name + '\'' +
                ", ps_Price=" + price +
                ", ps_Description='" + description + '\'' +
                ", ps_Status=" + status +
                ", ps_Type=" + type +
                ", ps_Started=" + started +
                ", ps_Property=" + property +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                '}';
    }
}
