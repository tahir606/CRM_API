package com.example.CRM.Task;

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

@Entity(name = "task_store")
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "taskID",
//        scope     = Integer.class)
public class Task  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "taskID")
    private int taskID;
    @Column(name = "subject")
    private String subject;
    @Column(name = "entryDate")
    private Timestamp entryDate;
    @Column(name = "dueDate")
    private Timestamp dueDate;
    @Column(name = "isRepeat")
    private int repeat;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private int status;
    @Column(name = "closedOn")
    private Timestamp closedOn;
    @Column(name = "notified")
    private int notified;
    @Column(name = "contactID")
    private Integer contactID;
    @Column(name = "createdOn")
    private Timestamp createdOn;
    @Column(name = "freeze")
    private int freeze;


    @Column(name = "psID")
    private Integer psID;
    @JsonBackReference(value = "pdTaskList")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "psID", insertable = false, updatable = false)
    private Product productTaskList;

    @Column(name = "createdBy")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy", insertable = false, updatable = false)
    private Users users;

    @Column(name = "leadsId")
    private Integer leadsId;
    @JsonBackReference(value = "ldTaskList")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leadsId", insertable = false, updatable = false)
    private Lead leadsTaskList;

    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clTaskList")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID",insertable = false, updatable = false)
    private Client clientTaskList;


    public Task(){

    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public Integer getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(Integer leadsId) {
        this.leadsId = leadsId;
    }

    public Product getProductTaskList() {
        return productTaskList;
    }

    public void setProductTaskList(Product productTaskList) {
        this.productTaskList = productTaskList;
    }



    public Lead getLeadsTaskList() {
        return leadsTaskList;
    }

    public void setLeadsTaskList(Lead leadsTaskList) {
        this.leadsTaskList = leadsTaskList;
    }

    public Client getClientTaskList() {
        return clientTaskList;
    }

    public void setClientTaskList(Client clientTaskList) {
        this.clientTaskList = clientTaskList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
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

    public Timestamp getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Timestamp closedOn) {
        this.closedOn = closedOn;
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

    public Integer getPsID() {
        return psID;
    }

    public void setPsID(Integer psID) {
        this.psID = psID;
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

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", subject='" + subject + '\'' +
                ", entryDate=" + entryDate +
                ", dueDate=" + dueDate +
                ", repeat=" + repeat +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", closedOn=" + closedOn +
                ", notified=" + notified +
                ", contactID=" + contactID +
                ", createdOn=" + createdOn +
                ", freeze=" + freeze +
                ", psID=" + psID +
                ", productTaskList=" + productTaskList +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", leadsId=" + leadsId +
                ", leadsTaskList=" + leadsTaskList +
                ", clientID=" + clientID +
                ", clientTaskList=" + clientTaskList +
                '}';
    }
}
