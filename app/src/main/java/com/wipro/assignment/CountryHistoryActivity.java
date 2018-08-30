package com.wipro.assignment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.wipro.assignment.adapter.CountryHistoryAdapter;
import com.wipro.assignment.customs.ProgressLoading;
import com.wipro.assignment.databinding.ActivityCountryHistoryBinding;
import com.wipro.assignment.model.CountryHistory;
import com.wipro.assignment.model.CountryHistoryListResponse;
import com.wipro.assignment.rest.Call;
import com.wipro.assignment.util.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by varora on 28-08-2018.
 */
public class CountryHistoryActivity extends BaseActivity {

    private ActivityCountryHistoryBinding binding;
    private Call actionCall;
    private List<CountryHistory> mList;
    private String mTitle;
    private ProgressLoading mProgressLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_history);

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mList = savedInstanceState.getParcelableArrayList(Constants.LIST);
            setTitle(savedInstanceState.getString(Constants.TITLE));
        } else {
            mList = new ArrayList<>();
            setTitle("");
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_country_history);
        binding.countryHistoryRecyclerView.setLayoutManager(new LinearLayoutManager
                (this));
        mProgressLoading = new ProgressLoading(this);
        mProgressLoading.onShow();

        fetchList(false);
        binding.refreshLayout.setColorSchemeColors(ContextCompat.getColor(this,
                R.color.statusBarColor));
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchList(true);
            }
        });
    }

    private void fetchList(boolean isRefresh) {
        if (!isRefresh)
            mProgressLoading.onShow();
        else
            binding.refreshLayout.setRefreshing(true);

        actionCall = new Call(this, new Call.Delegate() {
            @Override
            public void onSuccess(CountryHistoryListResponse response) {
                mProgressLoading.dismiss();
                if (binding.refreshLayout.isRefreshing()) {
                    binding.refreshLayout.setRefreshing(false);
                }

                mTitle = response.title;
                setTitle(mTitle);
                mList = response.list;
                binding.countryHistoryRecyclerView.setAdapter(new CountryHistoryAdapter
                        (mList));
            }

            @Override
            public void onFailure(Object t) {
                mProgressLoading.dismiss();
                if (binding.refreshLayout.isRefreshing()) {
                    binding.refreshLayout.setRefreshing(false);
                }
                Toast.makeText(getApplicationContext(), "Error = " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        actionCall.execute();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Constants.LIST, (ArrayList<? extends Parcelable>) mList);
        outState.putString(Constants.TITLE, mTitle);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.refreshLayout.setOnRefreshListener(null);
        if (actionCall != null)
            actionCall = null;
    }
}

