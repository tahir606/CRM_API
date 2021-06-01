package com.example.CRM.Event;

import com.example.CRM.Client.Client;
import com.example.CRM.LeadStore.Lead;
import com.example.CRM.Product.Store.Product;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "event_store")
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "eventID",
//        scope     = Integer.class)
public class Event  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eventID")
    private int eventID;
    @Column(name = "tittle")
    private String tittle;
    @Column(name = "location")
    private String location;
    @Column(name = "isEventAllDay")
    private int eventAllDay;
    @Column(name = "fromDate")
    private Timestamp from;
    @Column(name = "toDate")
    private Timestamp to;
    @Column(name = "description")
    private String description;
    @Column(name = "notified")
    private int notified;
    @Column(name = "CreatedOn")
    private Timestamp createdOn;
    @Column(name = "Freeze")
    private int freeze;
    @Column(name = "closedOn")
    private Timestamp closedOn;
    @Column(name = "status")
    private int status;

    @Column(name = "createdBy")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy", insertable = false, updatable = false)
    private Users users;

    @Column(name = "productID")
    private Integer productID;
    @JsonBackReference(value = "pdEventList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    private Product productEventList;


    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clEventList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client clientEventList;

    @Column(name = "leadsId")
    private Integer leadsId;
    @JsonBackReference(value = "ldEventList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leadsId", insertable = false, updatable = false)
    private Lead leadsEventList;


    public Event(){

    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(Integer leadsId) {
        this.leadsId = leadsId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEventAllDay() {
        return eventAllDay;
    }

    public void setEventAllDay(int eventAllDay) {
        this.eventAllDay = eventAllDay;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNotified() {
        return notified;
    }

    public void setNotified(int notified) {
        this.notified = notified;
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

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public Timestamp getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Timestamp closedOn) {
        this.closedOn = closedOn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Client getClientEventList() {
        return clientEventList;
    }

    public void setClientEventList(Client clientEventList) {
        this.clientEventList = clientEventList;
    }


    public Product getProductEventList() {
        return productEventList;
    }

    public void setProductEventList(Product productEventList) {
        this.productEventList = productEventList;
    }

    public Lead getLeadsEventList() {
        return leadsEventList;
    }

    public void setLeadsEventList(Lead leadsEventList) {
        this.leadsEventList = leadsEventList;
    }



    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", tittle='" + tittle + '\'' +
                ", location='" + location + '\'' +
                ", eventAllDay=" + eventAllDay +
                ", from=" + from +
                ", to=" + to +
                ", description='" + description + '\'' +
                ", notified=" + notified +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", createdOn=" + createdOn +
                ", freeze=" + freeze +
                ", closedOn=" + closedOn +
                ", status=" + status +
                ", productID=" + productID +
                ", productEventList=" + productEventList +
                ", clientID=" + clientID +
                ", clientEventList=" + clientEventList +
                ", leadsId=" + leadsId +
                ", leadsEventList=" + leadsEventList +
                '}';
    }
}
