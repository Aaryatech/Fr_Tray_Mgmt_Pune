package com.ats.patna_fr_tray_mgmt.bean;

public class TrayMgtDetailInTray {

    private int intrayId;
    private int tranDetailId;
    private int tranId;
    private int frId;
    private String intrayDate;
    private int exInt1;
    private int exInt2;
    private String exVar1;
    private String exVar2;
    private int delStatus;
    private int intrayBig;
    private int intrayLead;
    private int tranIntrayId;
    private int intraySmall;

    public TrayMgtDetailInTray(int intrayId, int tranDetailId, int tranId, int frId, String intrayDate, int delStatus, int intrayBig, int intrayLead, int tranIntrayId, int intraySmall) {
        this.intrayId = intrayId;
        this.tranDetailId = tranDetailId;
        this.tranId = tranId;
        this.frId = frId;
        this.intrayDate = intrayDate;
        this.delStatus = delStatus;
        this.intrayBig = intrayBig;
        this.intrayLead = intrayLead;
        this.tranIntrayId = tranIntrayId;
        this.intraySmall = intraySmall;
    }

    public int getIntrayId() {
        return intrayId;
    }

    public void setIntrayId(int intrayId) {
        this.intrayId = intrayId;
    }

    public int getTranDetailId() {
        return tranDetailId;
    }

    public void setTranDetailId(int tranDetailId) {
        this.tranDetailId = tranDetailId;
    }

    public int getTranId() {
        return tranId;
    }

    public void setTranId(int tranId) {
        this.tranId = tranId;
    }

    public int getFrId() {
        return frId;
    }

    public void setFrId(int frId) {
        this.frId = frId;
    }

    public String getIntrayDate() {
        return intrayDate;
    }

    public void setIntrayDate(String intrayDate) {
        this.intrayDate = intrayDate;
    }

    public int getExInt1() {
        return exInt1;
    }

    public void setExInt1(int exInt1) {
        this.exInt1 = exInt1;
    }

    public int getExInt2() {
        return exInt2;
    }

    public void setExInt2(int exInt2) {
        this.exInt2 = exInt2;
    }

    public String getExVar1() {
        return exVar1;
    }

    public void setExVar1(String exVar1) {
        this.exVar1 = exVar1;
    }

    public String getExVar2() {
        return exVar2;
    }

    public void setExVar2(String exVar2) {
        this.exVar2 = exVar2;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    public int getIntrayBig() {
        return intrayBig;
    }

    public void setIntrayBig(int intrayBig) {
        this.intrayBig = intrayBig;
    }

    public int getIntrayLead() {
        return intrayLead;
    }

    public void setIntrayLead(int intrayLead) {
        this.intrayLead = intrayLead;
    }

    public int getIntraySmall() {
        return intraySmall;
    }

    public void setIntraySmall(int intraySmall) {
        this.intraySmall = intraySmall;
    }

    public int getTranIntrayId() {
        return tranIntrayId;
    }

    public void setTranIntrayId(int tranIntrayId) {
        this.tranIntrayId = tranIntrayId;
    }

    @Override
    public String toString() {
        return "TrayMgtDetailInTray [intrayId=" + intrayId + ", tranDetailId=" + tranDetailId + ", tranId=" + tranId
                + ", frId=" + frId + ", intrayDate=" + intrayDate + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
                + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", delStatus=" + delStatus + ", intrayBig=" + intrayBig
                + ", intrayLead=" + intrayLead + ", tranIntrayId=" + tranIntrayId + ", intraySmall=" + intraySmall
                + "]";
    }
}
