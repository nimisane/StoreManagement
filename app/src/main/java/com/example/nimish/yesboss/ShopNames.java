package com.example.nimish.yesboss;

import androidx.annotation.NonNull;

public class ShopNames {
    private String shop_name;

    public ShopNames() {
    }

    public ShopNames(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @NonNull
    @Override
    public String toString() {
        return shop_name;
    }
}
