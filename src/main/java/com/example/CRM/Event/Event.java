package com.example.CRM.Event;

import com.example.CRM.Client.Client;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "event_store")
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
    @Column(name = "leadsId")
    private int leadsId;
    @Column(name = "productID")
    private int productID;
    @Column(name = "createdBy")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy", insertable = false, updatable = false)
    private Users users;
    @Column(name = "CreatedOn")
    private Timestamp createdOn;
    @Column(name = "Freeze")
    private int freeze;
    @Column(name = "closedOn")
    private Timestamp closedOn;
    @Column(name = "status")
    private int status;
    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clEventList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client clientEventList;


    public Event(){

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

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public int getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(int leadsId) {
        this.leadsId = leadsId;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
                ", leadsId=" + leadsId +
                ", productID=" + productID +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", freeze=" + freeze +
                ", closedOn=" + closedOn +
                ", status=" + status +
                ", clientID=" + clientID +
                ", clientEventList=" + clientEventList +
                '}';
    }
}
