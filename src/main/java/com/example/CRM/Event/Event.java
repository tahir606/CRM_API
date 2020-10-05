package com.example.CRM.Event;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "event_store")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Event_ID")
    private int eventID;
    @Column(name = "Event_Tittle")
    private String tittle;
    @Column(name = "Event_Location")
    private String location;
    @Column(name = "Event_AllDay")
    private int eventAllDay;
    @Column(name = "Event_From")
    private Date from;
    @Column(name = "Event_To")
    private Date to;
    @Column(name = "Event_Description")
    private String description;
    @Column(name = "Event_Notified")
    private int notified;
    @Column(name = "Cl_ID")
    private int clientID;
    @Column(name = "Leads_ID")
    private int leadsId;
    @Column(name = "Product_ID")
    private int productID;
    @Column(name = "CreatedBy")
    private int createdBy;
    @Column(name = "CreatedOn")
    private Date createdOn;
    @Column(name = "Freeze")
    private int freeze;
    @Column(name = "Event_ClosedOn")
    private Date closedOn;
    @Column(name = "Event_Status")
    private int status;

    public Event(){

    }

    public Event(int eventID, String tittle, String location, int eventAllDay, Date event_From, Date event_To, String description,
                 int notified, int clientID, int leadsId, int productID, int createdBy, Date createdOn, int freeze, Date closedOn,
                 int status) {
        this.eventID = eventID;
        this.tittle = tittle;
        this.location = location;
        this.eventAllDay = eventAllDay;
        from = event_From;
        to = event_To;
        this.description = description;
        this.notified = notified;
        this.clientID = clientID;
        this.leadsId = leadsId;
        this.productID = productID;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.freeze = freeze;
        this.closedOn = closedOn;
        this.status = status;
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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public Date getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Date closedOn) {
        this.closedOn = closedOn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_ID=" + eventID +
                ", event_Tittle='" + tittle + '\'' +
                ", event_Location='" + location + '\'' +
                ", event_EventAllDay=" + eventAllDay +
                ", Event_From=" + from +
                ", Event_To=" + to +
                ", event_Description='" + description + '\'' +
                ", event_Notified=" + notified +
                ", client_ID=" + clientID +
                ", leads_id=" + leadsId +
                ", product_ID=" + productID +
                ", CreatedBy=" + createdBy +
                ", CreatedOn=" + createdOn +
                ", freeze=" + freeze +
                ", event_ClosedOn=" + closedOn +
                ", event_Status=" + status +
                '}';
    }
}
