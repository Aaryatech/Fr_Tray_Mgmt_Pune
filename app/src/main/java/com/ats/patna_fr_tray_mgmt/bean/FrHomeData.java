package com.ats.patna_fr_tray_mgmt.bean;

import java.util.List;

public class FrHomeData {

    private OpeningCount openingCount;
    private List<ReceivedTrayList> receivedTrayList = null;
    private List<ReturnTrayList> returnTrayList = null;

    public OpeningCount getOpeningCount() {
        return openingCount;
    }

    public void setOpeningCount(OpeningCount openingCount) {
        this.openingCount = openingCount;
    }

    public List<ReceivedTrayList> getReceivedTrayList() {
        return receivedTrayList;
    }

    public void setReceivedTrayList(List<ReceivedTrayList> receivedTrayList) {
        this.receivedTrayList = receivedTrayList;
    }

    public List<ReturnTrayList> getReturnTrayList() {
        return returnTrayList;
    }

    public void setReturnTrayList(List<ReturnTrayList> returnTrayList) {
        this.returnTrayList = returnTrayList;
    }

    @Override
    public String toString() {
        return "FrHomeData{" +
                "openingCount=" + openingCount +
                ", receivedTrayList=" + receivedTrayList +
                ", returnTrayList=" + returnTrayList +
                '}';
    }
}
