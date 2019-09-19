package com.ats.patna_fr_tray_mgmt.bean;

import java.util.List;

/**
 * Created by MAXADMIN on 30/1/2018.
 */

public class NoticeData {

    private List<SchedulerList> schedulerList;
    private Info info;

    public List<SchedulerList> getSchedulerList() {
        return schedulerList;
    }

    public void setSchedulerList(List<SchedulerList> schedulerList) {
        this.schedulerList = schedulerList;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "NoticeData{" +
                "schedulerList=" + schedulerList +
                ", info=" + info +
                '}';
    }
}
