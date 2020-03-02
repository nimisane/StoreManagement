package com.example.nimish.yesboss;

import java.util.List;

public class PatternItems {

    String pCode;
    String imageLink;
    String category;

    public PatternItems() {

    }

    public PatternItems(String pCode, String imageLink, String category) {
        this.pCode = pCode;
        this.imageLink = imageLink;
        this.category = category;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
