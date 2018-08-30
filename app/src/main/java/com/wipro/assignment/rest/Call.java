package com.wipro.assignment.rest;

import android.content.Context;

import com.wipro.assignment.model.CountryHistoryListResponse;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by varora on 28-08-2018.
 */
public class Call {

    private retrofit2.Call<CountryHistoryListResponse> call;
    private Delegate delegate;

    public Call(Context context, Delegate delegate) {
        this.delegate = delegate;
    }

    public void execute() {
        CallInterface callInterface = Api.getClient().create(CallInterface.class);

        call = callInterface.getCountryHistory();
        call.enqueue(new Callback<CountryHistoryListResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CountryHistoryListResponse> call, Response<CountryHistoryListResponse> response) {
                int statusCode = response.code();
                if (statusCode == Config.RESP_OK) {
                    delegate.onSuccess(response.body());
                } else {
                    delegate.onFailure("On Failure - " + statusCode);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CountryHistoryListResponse> call, Throwable t) {
                delegate.onFailure(t.getMessage());
            }
        });

    }

    public interface Delegate {
        void onSuccess(CountryHistoryListResponse response);

        void onFailure(Object t);
    }

}
