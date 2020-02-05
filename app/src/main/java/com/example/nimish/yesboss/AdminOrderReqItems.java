package com.example.nimish.yesboss;

public class AdminOrderReqItems {

    private int OrdReqImg;
    private String OrdReqTime;
    private String OrdReqStatus;

    public AdminOrderReqItems(int ordReqImg, String ordReqTime, String ordReqStatus) {
        OrdReqImg = ordReqImg;
        OrdReqTime = ordReqTime;
        OrdReqStatus = ordReqStatus;
    }

    public int getOrdReqImg() {
        return OrdReqImg;
    }

    public String getOrdReqTime() {
        return OrdReqTime;
    }

    public String getOrdReqStatus() {
        return OrdReqStatus;
    }
}
