package com.example.CRM.Task;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "task_store")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_ID")
    private int taskID;
    @Column(name = "task_Subject")
    private String subject;
    @Column(name = "task_entry_date")
    private Date entryDate;
    @Column(name = "task_due_date")
    private Date dueDate;
    @Column(name = "task_Repeat")
    private int repeat;
    @Column(name = "task_Description")
    private String description;
    @Column(name = "task_Status")
    private int status;
    @Column(name = "task_ClosedOn")
    private Date closedOn;
    @Column(name = "task_Notified")
    private int notified;
    @Column(name = "Contact_ID")
    private int contactID;
    @Column(name = "Client_ID")
    private int clientID;
    @Column(name = "Product_ID")
    private int psID;
    @Column(name = "Leads_ID")
    private int leadsId;
    @Column(name = "task_CreatedBy")
    private int createdBy;
    @Column(name = "task_CreatedOn")
    private Date createdOn;
    @Column(name = "task_Freeze")
    private int freeze;

    public Task(){

    }
    public Task(int taskID, String subject, Date entryDate, Date dueDate, int repeat, String description, int status, Date closedOn,
                int notified, int contactID, int clientID, int psID, int leadsId, int createdBy, Date createdOn, int freeze) {
        this.taskID = taskID;
        this.subject = subject;
        this.entryDate = entryDate;
        this.dueDate = dueDate;
        this.repeat = repeat;
        this.description = description;
        this.status = status;
        this.closedOn = closedOn;
        this.notified = notified;
        this.contactID = contactID;
        this.clientID = clientID;
        this.psID = psID;
        this.leadsId = leadsId;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.freeze = freeze;
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

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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

    public Date getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Date closedOn) {
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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
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
