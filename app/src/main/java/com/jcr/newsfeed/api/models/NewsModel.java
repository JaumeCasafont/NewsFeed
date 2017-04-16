package com.jcr.newsfeed.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jaucr on 08/04/2017.
 */

public class NewsModel implements Parcelable {

    private String _id;
    private long index;
    private String picture;
    private String text;
    private String thumbnail;
    private String title;
    private ArrayList<PeopleModel> people = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();

    public NewsModel(){}

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

    public void setIndex(long index) {
        this.index = index;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getIndex() {
        return index;
    }

    public ArrayList<PeopleModel> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<PeopleModel> people) {
        this.people = people;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private NewsModel(Parcel in) {
        _id = in.readString();
        index = in.readLong();
        picture = in.readString();
        text = in.readString();
        thumbnail = in.readString();
        title = in.readString();
        people = in.createTypedArrayList(PeopleModel.CREATOR);
        tags = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this._id);
        parcel.writeLong(this.index);
        parcel.writeString(this.picture);
        parcel.writeString(this.text);
        parcel.writeString(this.thumbnail);
        parcel.writeString(this.title);
        parcel.writeList(this.people);
        parcel.writeList(this.tags);
    }
}
