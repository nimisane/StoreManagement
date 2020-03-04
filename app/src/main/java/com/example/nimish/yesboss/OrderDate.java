package com.example.nimish.yesboss;

import androidx.annotation.NonNull;

public class OrderDate {

    String ordDate;

    public OrderDate() {

    }

    public OrderDate(String ordDate) {
        this.ordDate = ordDate;
    }

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }

    @NonNull
    @Override
    public String toString() {
        return ordDate;
    }
}
