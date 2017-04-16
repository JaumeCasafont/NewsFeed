package com.jcr.newsfeed.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jaucr on 08/04/2017.
 */

public class NewsListModel implements Serializable {

    private ArrayList<NewsModel> newsArray = new ArrayList<>();

    public ArrayList<NewsModel> getNewsArray() {
        return newsArray;
    }

    public NewsListModel(){}

    public void setNewsArray(ArrayList<NewsModel> newsArray) {
        this.newsArray = newsArray;
    }
}
