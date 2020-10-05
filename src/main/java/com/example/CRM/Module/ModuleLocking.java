package com.example.CRM.Module;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "module_locking")
public class ModuleLocking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Module_ID")
    private int moduleId;
    @Column(name = "PM_ID")
    private int pmID;
    @Column(name = "User_CODE")
    private int userCode;
    @Column(name = "Locked_Time")
    private Date lockedTime;
    @Column(name = "UnLocked_Time")
    private Date unLockedTime;
    @Column(name = "Description")
    private String description;
    @Column(name = "PS_ID")
    private int psID;

    public ModuleLocking() {

    }

    public ModuleLocking(int moduleId, int pmID, int userCode, Date lockedTime, Date unLockedTime, String description, int psID) {
        this.moduleId = moduleId;
        this.pmID = pmID;
        this.userCode = userCode;
        this.lockedTime = lockedTime;
        this.unLockedTime = unLockedTime;
        this.description = description;
        this.psID = psID;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getPmID() {
        return pmID;
    }

    public void setPmID(int pmID) {
        this.pmID = pmID;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public Date getUnLockedTime() {
        return unLockedTime;
    }

    public void setUnLockedTime(Date unLockedTime) {
        this.unLockedTime = unLockedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPsID() {
        return psID;
    }

    public void setPsID(int psID) {
        this.psID = psID;
    }

    @Override
    public String toString() {
        return "ModuleLocking{" +
                "moduleId=" + moduleId +
                "pm_ID=" + pmID +
                ", userCode=" + userCode +
                ", locked_Time=" + lockedTime +
                ", unLocked_Time=" + unLockedTime +
                ", description='" + description + '\'' +
                ", ps_ID=" + psID +
                '}';
    }
}
