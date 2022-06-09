package com.example.plants_pro;

import android.graphics.Bitmap;

public class item {
    private Bitmap bitmap;
    private String name;

    public item(Bitmap bitmap, String name){
        this.bitmap = bitmap;
        this.name = name;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public String getName(){
        return name;
    }
}
