package com.example.nimish.yesboss;

import java.util.List;

public class AdminOrderItem {
    String productName;
    String productCode;
    String shopName;
    String category;
    List<String> imgLink;

    public AdminOrderItem() {

    }

    public AdminOrderItem(String productName, String productCode, String shopName, String category, List<String> imgLink) {
        this.productName = productName;
        this.productCode = productCode;
        this.shopName = shopName;
        this.category = category;
        this.imgLink = imgLink;
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
}
