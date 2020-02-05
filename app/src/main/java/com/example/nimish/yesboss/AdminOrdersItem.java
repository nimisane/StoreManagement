package com.example.nimish.yesboss;

public class AdminOrdersItem {
    private int orderImg;
    private String orderTime;
    private String orderStatus;

    public AdminOrdersItem(int orderImg, String orderTime, String orderStatus) {
        this.orderImg = orderImg;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public int getOrderImg() {
        return orderImg;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
