package com.example.nimish.yesboss;

public class UserItems {

    private String shop_name;
    private String email;
    private String address;
    private String profile_type;

    public UserItems() {

    }

    public UserItems(String shop_name, String email, String address, String profile_type) {
        this.shop_name = shop_name;
        this.email = email;
        this.address = address;
        this.profile_type = profile_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getProfile_type() {
        return profile_type;
    }

    public void setProfile_type(String profile_type) {
        this.profile_type = profile_type;
    }
}
