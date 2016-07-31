package com.abulkay.foodierater.util;

import java.io.Serializable;

/**
 * Created by akay on 4/3/16.
 */
public class Item implements Serializable {
    private String mImageUrl;
    private String mMealName;
    private float mRating;

    public Item(String mImageUrl, String mMealName, float mRating) {
        this.mImageUrl = mImageUrl;
        this.mMealName = mMealName;
        this.mRating = mRating;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getMealName() {
        return mMealName;
    }

    public void setMealName(String mMealName) {
        this.mMealName = mMealName;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float mRating) {
        this.mRating = mRating;
    }
}
