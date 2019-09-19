package com.ats.patna_fr_tray_mgmt.bean;

public class ReceivedTrayList {

    private String id;
    private Integer small;
    private Integer lead;
    private Integer big;
    private String trayDate;
    private Integer headerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSmall() {
        return small;
    }

    public void setSmall(Integer small) {
        this.small = small;
    }

    public Integer getLead() {
        return lead;
    }

    public void setLead(Integer lead) {
        this.lead = lead;
    }

    public Integer getBig() {
        return big;
    }

    public void setBig(Integer big) {
        this.big = big;
    }

    public String getTrayDate() {
        return trayDate;
    }

    public void setTrayDate(String trayDate) {
        this.trayDate = trayDate;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    @Override
    public String toString() {
        return "OpeningCount{" +
                "id='" + id + '\'' +
                ", small=" + small +
                ", lead=" + lead +
                ", big=" + big +
                ", trayDate='" + trayDate + '\'' +
                ", headerId=" + headerId +
                '}';
    }


}
