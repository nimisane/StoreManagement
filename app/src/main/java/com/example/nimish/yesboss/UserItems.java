package com.example.nimish.yesboss;

public class UserItems {

    private String shopName;
    private String shopUserId;
    private String shopPwd;
    private int photo;

    public UserItems(String shopName, String shopUserId, String shopPwd, int photo) {
        this.shopName = shopName;
        this.shopUserId = shopUserId;
        this.shopPwd = shopPwd;
        this.photo = photo;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopUserId() {
        return shopUserId;
    }

    public String getShopPwd() {
        return shopPwd;
    }

    public int getPhoto() {
        return photo;
    }
}
