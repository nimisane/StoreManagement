package com.example.nimish.yesboss;

import java.util.List;
import java.util.Map;

public class AdminOrderItem {
    String productName;
    String productCode;
    String shopName;
    String category;
    List<String> imgLink;
    Map<String,Integer> orderData;
    String mrp;
    Object orderDate;
    Object sortDate;
    Object dateOnly;
    String orderStatus;

    public AdminOrderItem() {

    }


    public AdminOrderItem(String productName, String productCode, String shopName, String category, List<String> imgLink, Map<String, Integer> orderData, String mrp, Object orderDate, Object sortDate, Object dateOnly, String orderStatus) {
        this.productName = productName;
        this.productCode = productCode;
        this.shopName = shopName;
        this.category = category;
        this.imgLink = imgLink;
        this.orderData = orderData;
        this.mrp = mrp;
        this.orderDate = orderDate;
        this.sortDate = sortDate;
        this.dateOnly = dateOnly;
        this.orderStatus = orderStatus;
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

    public Object getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Object orderDate) {
        this.orderDate = orderDate;
    }

    public Map<String, Integer> getOrderData() {
        return orderData;
    }

    public void setOrderData(Map<String, Integer> orderData) {
        this.orderData = orderData;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}