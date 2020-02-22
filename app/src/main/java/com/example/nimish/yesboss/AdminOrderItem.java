package com.example.nimish.yesboss;

import java.util.List;

public class AdminOrderItem {
    String productName;
    String productCode;
    String shopName;
    String category;
    List<String> imgLink;
    String mrp;
    String orderDate;
    String orderTime;

    public AdminOrderItem() {

    }

    public AdminOrderItem(String productName, String productCode, String shopName, String category, List<String> imgLink, String mrp, String orderDate, String orderTime) {
        this.productName = productName;
        this.productCode = productCode;
        this.shopName = shopName;
        this.category = category;
        this.imgLink = imgLink;
        this.mrp = mrp;
        this.orderDate = orderDate;
        this.orderTime = orderTime;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getImgLink() {
        return imgLink;
    }

    public void setImgLink(List<String> imgLink) {
        this.imgLink = imgLink;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
