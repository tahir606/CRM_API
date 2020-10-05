package com.example.CRM.NotificationSettings;

import javax.persistence.*;

@Entity
@Table(name = "notification_settings")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_Id")
    private int notificationId;
    @Column(name = "notification_Task")
    private int task;
    @Column(name = "notification_Event")
    private int event;

    public Notification(){

    }

    public Notification(int notificationId, int task, int event) {
        this.notificationId = notificationId;
        this.task = task;
        this.event = event;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", notificationTask=" + task +
                ", notificationEvent=" + event +
                '}';
    }
}
