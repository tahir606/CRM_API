package com.example.CRM.Rights.RightsChart;

import javax.persistence.*;

@Entity
@Table(name = "rights_chart")
public class RightChart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RightsChart_ID")
    private int rightsChartId;
    @Column(name = "Rights_Code")
    private int rightsCode;
    @Column(name = "User_CODE")
    private int userCode;
    public RightChart(){

    }
    public RightChart(int rightsChartId, int rightsCode, int userCode) {
        this.rightsChartId = rightsChartId;
        this.rightsCode = rightsCode;
        this.userCode = userCode;
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
