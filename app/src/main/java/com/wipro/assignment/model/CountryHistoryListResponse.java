package com.wipro.assignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by varora on 28-08-2018.
 */

public class CountryHistoryListResponse {

    @SerializedName("title")
    public String title;

    @SerializedName("rows")
    public List<CountryHistory> list;
}
