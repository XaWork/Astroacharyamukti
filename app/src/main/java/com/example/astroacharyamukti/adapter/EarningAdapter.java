package com.example.astroacharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.model.EarnDetails;

import java.util.List;


public class EarningAdapter extends RecyclerView.Adapter<EarningAdapter.ViewHolder> {
    Context context;
    List<EarnDetails> earnDetails;

    public EarningAdapter(Context context, List<EarnDetails> earningData) {
        this.context = context;
        this.earnDetails = earningData;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_earning_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return earnDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
