package com.example.nimish.yesboss;

import android.net.Uri;

import androidx.annotation.NonNull;

public class AdminProductPhotoItems {

    String photoPath;
    String photoName;
    Uri img;

    public AdminProductPhotoItems(String photoPath, String photoName,Uri img) {
        this.photoPath = photoPath;
        this.photoName = photoName;
        this.img = img;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Uri getImg() {
        return img;
    }

    @NonNull
    @Override
    public String toString() {
        return photoName;
    }
}


