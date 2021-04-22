package com.example.CRM.Rights.RightsChart;

import com.example.CRM.Rights.RightsList;
import com.example.CRM.User.Users;

import javax.persistence.*;

@Entity(name = "rights_chart")
//@Table(name = "rights_chart")
public class RightChart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RightsChart_ID")
    private int rightsChartId;
    @Column(name = "Rights_Code")
    private int rightsCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Rights_Code", insertable = false, updatable = false)
    private RightsList rightsList;
    @Column(name = "User_CODE")
    private int userCode;

    public RightChart(){

    }

    public RightChart(int rightsChartId, int rightsCode, RightsList rightsList, int userCode) {
        this.rightsChartId = rightsChartId;
        this.rightsCode = rightsCode;
        this.rightsList = rightsList;
        this.userCode = userCode;
    }

    public RightsList getRightsList() {
        return rightsList;
    }

    public void setRightsList(RightsList rightsList) {
        this.rightsList = rightsList;
    }

    public int getRightsChartId() {
        return rightsChartId;
    }

    public void setRightsChartId(int rightsChartId) {
        this.rightsChartId = rightsChartId;
    }

    public int getRightsCode() {
        return rightsCode;
    }

    public void setRightsCode(int rightsCode) {
        this.rightsCode = rightsCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "RightChart{" +
                "rightsChartId=" + rightsChartId +
                ", rightsCode=" + rightsCode +
                ", userCode=" + userCode +
                '}';
    }
}
