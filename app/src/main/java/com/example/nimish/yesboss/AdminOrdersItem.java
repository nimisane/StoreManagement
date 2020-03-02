package com.example.nimish.yesboss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminOrdersItem {
    String productName;
    String productCode;
    String shopName;
    String category;
    List<String> imageLink;
    //ArrayList<AdminSendOrderImageItem> imgLink;
    Map<String,String> orderData;
    String mrp;
    Object orderDate;
    Object sortDate;
    Object dateOnly;

//    public AdminOrdersItem(String productName, String productCode, String shopName, String category, ArrayList<AdminSendOrderImageItem> imgLink, Map<String, String> orderData, String mrp, Object orderDate, Object sortDate) {
//        this.productName = productName;
//        this.productCode = productCode;
//        this.shopName = shopName;
//        this.category = category;
//        this.imgLink = imgLink;
//        this.orderData = orderData;
//        this.mrp = mrp;
//        this.orderDate = orderDate;
//        this.sortDate = sortDate;
//    }


    public AdminOrdersItem() {

    }

    public AdminOrdersItem(String productName, String productCode, String shopName, String category, List<String> imageLink, Map<String, String> orderData, String mrp, Object orderDate, Object sortDate, Object dateOnly) {
        this.productName = productName;
        this.productCode = productCode;
        this.shopName = shopName;
        this.category = category;
        this.imageLink = imageLink;
        this.orderData = orderData;
        this.mrp = mrp;
        this.orderDate = orderDate;
        this.sortDate = sortDate;
        this.dateOnly = dateOnly;
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

    public Map<String, String> getOrderData() {
        return orderData;
    }

    public void setOrderData(Map<String, String> orderData) {
        this.orderData = orderData;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public Object getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Object orderDate) {
        this.orderDate = orderDate;
    }

    public Object getSortDate() {
        return sortDate;
    }

    public void setSortDate(Object sortDate) {
        this.sortDate = sortDate;
    }

    public Object getDateOnly() {
        return dateOnly;
    }

    public void setDateOnly(Object dateOnly) {
        this.dateOnly = dateOnly;
    }

    public List<String> getImageLink() {
        return imageLink;
    }

    public void setImageLink(List<String> imageLink) {
        this.imageLink = imageLink;
    }
}
