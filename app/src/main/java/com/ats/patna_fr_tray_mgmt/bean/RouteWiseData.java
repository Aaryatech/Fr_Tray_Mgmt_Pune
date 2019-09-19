package com.ats.patna_fr_tray_mgmt.bean;

public class RouteWiseData {


    private Integer tranId;
    private String tranDate;
    private Integer vehId;
    private Integer drvId;
    private String driverName;
    private Integer routeId;
    private String routeName;
    private String vehNo;
    private String vehOuttime;
    private String vehIntime;
    private Integer vehOutkm;
    private Integer vehInkm;
    private Integer vehRunningKm;
    private Integer diesel;
    private Integer vehStatus;
    private Integer delStatus;
    private Integer extraTrayOut;
    private Integer extraTrayIn;
    private Integer vehIsRegular;
    private Integer isSameDay;


    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public Integer getVehId() {
        return vehId;
    }

    public void setVehId(Integer vehId) {
        this.vehId = vehId;
    }

    public Integer getDrvId() {
        return drvId;
    }

    public void setDrvId(Integer drvId) {
        this.drvId = drvId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getVehNo() {
        return vehNo;
    }

    public void setVehNo(String vehNo) {
        this.vehNo = vehNo;
    }

    public String getVehOuttime() {
        return vehOuttime;
    }

    public void setVehOuttime(String vehOuttime) {
        this.vehOuttime = vehOuttime;
    }

    public String getVehIntime() {
        return vehIntime;
    }

    public void setVehIntime(String vehIntime) {
        this.vehIntime = vehIntime;
    }

    public Integer getVehOutkm() {
        return vehOutkm;
    }

    public void setVehOutkm(Integer vehOutkm) {
        this.vehOutkm = vehOutkm;
    }

    public Integer getVehInkm() {
        return vehInkm;
    }

    public void setVehInkm(Integer vehInkm) {
        this.vehInkm = vehInkm;
    }

    public Integer getVehRunningKm() {
        return vehRunningKm;
    }

    public void setVehRunningKm(Integer vehRunningKm) {
        this.vehRunningKm = vehRunningKm;
    }

    public Integer getDiesel() {
        return diesel;
    }

    public void setDiesel(Integer diesel) {
        this.diesel = diesel;
    }

    public Integer getVehStatus() {
        return vehStatus;
    }

    public void setVehStatus(Integer vehStatus) {
        this.vehStatus = vehStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getExtraTrayOut() {
        return extraTrayOut;
    }

    public void setExtraTrayOut(Integer extraTrayOut) {
        this.extraTrayOut = extraTrayOut;
    }

    public Integer getExtraTrayIn() {
        return extraTrayIn;
    }

    public void setExtraTrayIn(Integer extraTrayIn) {
        this.extraTrayIn = extraTrayIn;
    }

    public Integer getVehIsRegular() {
        return vehIsRegular;
    }

    public void setVehIsRegular(Integer vehIsRegular) {
        this.vehIsRegular = vehIsRegular;
    }

    public Integer getIsSameDay() {
        return isSameDay;
    }

    public void setIsSameDay(Integer isSameDay) {
        this.isSameDay = isSameDay;
    }

    @Override
    public String toString() {
        return "RouteWiseData{" +
                "tranId=" + tranId +
                ", tranDate='" + tranDate + '\'' +
                ", vehId=" + vehId +
                ", drvId=" + drvId +
                ", driverName='" + driverName + '\'' +
                ", routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                ", vehNo='" + vehNo + '\'' +
                ", vehOuttime='" + vehOuttime + '\'' +
                ", vehIntime='" + vehIntime + '\'' +
                ", vehOutkm=" + vehOutkm +
                ", vehInkm=" + vehInkm +
                ", vehRunningKm=" + vehRunningKm +
                ", diesel=" + diesel +
                ", vehStatus=" + vehStatus +
                ", delStatus=" + delStatus +
                ", extraTrayOut=" + extraTrayOut +
                ", extraTrayIn=" + extraTrayIn +
                ", vehIsRegular=" + vehIsRegular +
                ", isSameDay=" + isSameDay +
                '}';
    }
}
