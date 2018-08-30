package com.wipro.assignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.wipro.assignment.R;
import com.bumptech.glide.Glide;
import com.wipro.assignment.model.CountryHistory;


/**
 * Created by varora on 28-08-2018.
 */

public class ItemViewModel extends BaseObservable {

    private String title;
    private String description;
    private String imageUrl;
    private CountryHistory history;


    public ItemViewModel(CountryHistory data) {
        this.history = data;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(view);
    }

    public String getTitle() {
        title = history.getTitle();
        return title;
    }

    public String getDescription() {
        description = history.getDescription();
        return description;
    }

    public String getImageUrl() {
        imageUrl = history.getImageUrl();
        return imageUrl;
    }
}