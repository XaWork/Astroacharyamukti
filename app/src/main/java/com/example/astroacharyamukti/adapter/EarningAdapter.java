package com.example.astroacharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        EarnDetails earn = earnDetails.get(position);
        holder.consultation_id.setText(earn.getConsultation_id());
        holder.name.setText(earn.getName());
        holder.consultation_type.setText(earn.getConsultation_type());
        holder.earnMount.setText(earn.getEaramount());
        holder.amount.setText(earn.getAmount());
        holder.duration.setText(earn.getDuration());
        holder.date.setText(earn.getDate());
    }

    @Override
    public int getItemCount() {
        return earnDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, consultation_id, consultation_type, earnMount, amount, duration, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_con_name);
            consultation_id = itemView.findViewById(R.id.text_consId);
            consultation_type = itemView.findViewById(R.id.text_con_call);
            earnMount = itemView.findViewById(R.id.earningAmount);
            amount = itemView.findViewById(R.id.amount);
            duration = itemView.findViewById(R.id.duration);
            date = itemView.findViewById(R.id.time);

        }
    }
}
