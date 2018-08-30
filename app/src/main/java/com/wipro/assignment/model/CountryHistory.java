package com.wipro.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by varora on 28-08-2018.
 */

public class CountryHistory implements Parcelable {

    public static final Parcelable.Creator<CountryHistory> CREATOR = new Parcelable.Creator<CountryHistory>() {
        @Override
        public CountryHistory createFromParcel(Parcel source) {
            return new CountryHistory(source);
        }

        @Override
        public CountryHistory[] newArray(int size) {
            return new CountryHistory[size];
        }
    };
    private String title;
    private String description;
    private String imageHref;

    public CountryHistory() {
    }

    private CountryHistory(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.imageHref = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageHref;
    }

    public void setImageUrl(String imageUrl) {
        this.imageHref = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.imageHref);
    }
}
