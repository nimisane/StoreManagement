package com.example.nimish.yesboss;

import androidx.annotation.NonNull;

public class AddCategoryItem {

    private String category_name;

    public AddCategoryItem() {
    }

    public AddCategoryItem(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    @NonNull
    @Override
    public String toString() {
        return category_name;
    }
}
