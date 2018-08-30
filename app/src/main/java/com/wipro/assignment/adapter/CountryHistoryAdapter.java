package com.wipro.assignment.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wipro.assignment.R;
import com.wipro.assignment.databinding.HistoryAdapterBinding;
import com.wipro.assignment.model.CountryHistory;
import com.wipro.assignment.viewmodel.ItemViewModel;

import java.util.List;

/**
 * Created by varora on 28-08-2018.
 */
public class CountryHistoryAdapter extends RecyclerView.Adapter
        <CountryHistoryAdapter.CountryViewHolder> {

    private List<CountryHistory> countryHistoryList;

    public CountryHistoryAdapter(List<CountryHistory> list) {
        this.countryHistoryList = list;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HistoryAdapterBinding binding = DataBindingUtil.inflate(LayoutInflater.
                        from(parent.getContext()), R.layout.history_adapter, parent,
                false);
        return new CountryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        HistoryAdapterBinding binding = holder.binding;
        binding.setItem(new ItemViewModel(countryHistoryList.get(position)));
    }

    @Override
    public int getItemCount() {
        return countryHistoryList.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        private HistoryAdapterBinding binding;

        CountryViewHolder(HistoryAdapterBinding binding) {
            super(binding.cardLayout);
            this.binding = binding;
        }
    }
}