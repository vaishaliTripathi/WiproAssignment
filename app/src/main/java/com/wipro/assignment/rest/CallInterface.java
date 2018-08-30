package com.wipro.assignment.rest;


import com.wipro.assignment.model.CountryHistoryListResponse;

import retrofit2.http.GET;

/**
 * Created by varora on 28-08-2018.
 */

public interface CallInterface {
    @GET("facts.json")
    retrofit2.Call<CountryHistoryListResponse> getCountryHistory();
}

