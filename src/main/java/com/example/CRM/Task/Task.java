package com.example.CRM.Task;

import com.example.CRM.Client.Client;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "task_store")
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
    private int contactID;
    @Column(name = "psID")
    private int psID;
    @Column(name = "leadsId")
    private int leadsId;
    @Column(name = "createdBy")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy", insertable = false, updatable = false)
    private Users users;
    @Column(name = "createdOn")
    private Timestamp createdOn;
    @Column(name = "freeze")
    private int freeze;
    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clTaskList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client clientTaskList;
    public Task(){

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

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public int getPsID() {
        return psID;
    }

    public void setPsID(int psID) {
        this.psID = psID;
    }

    public int getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(int leadsId) {
        this.leadsId = leadsId;
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

    public Client getClientTaskList() {
        return clientTaskList;
    }

    public void setClientTaskList(Client clientTaskList) {
        this.clientTaskList = clientTaskList;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", taskSubject='" + subject + '\'' +
                ", taskEntryDate=" + entryDate +
                ", taskDueDate=" + dueDate +
                ", taskRepeat=" + repeat +
                ", taskDescription=" + description +
                ", taskStatus=" + status +
                ", taskClosedOn=" + closedOn +
                ", taskNotified=" + notified +
                ", contactID=" + contactID +
                ", clientID=" + clientID +
                ", psID=" + psID +
                ", leadsId=" + leadsId +
                ", taskCreatedBy=" + createdBy +
                ", taskCreatedOn=" + createdOn +
                ", taskFreeze=" + freeze +
                '}';
    }
}
