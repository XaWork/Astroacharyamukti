package com.example.astroacharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.model.BankDetail;

import java.util.List;

public class BankDetailsAdapter extends RecyclerView.Adapter<BankDetailsAdapter.ViewHolder> {
    List<BankDetail> dataList;
    Context context;

    public BankDetailsAdapter(Context context, List<BankDetail> dataList) {
        this.dataList = dataList;
        this.context = context;
    }


    @NonNull
    @Override
    public BankDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bank_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankDetailsAdapter.ViewHolder holder, int position) {
        BankDetail data = dataList.get(position);
        holder.bfname.setText(data.getbFName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bfname, bank, account, type, ifsc1, branch, address, pan1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bfname = itemView.findViewById(R.id.txtName);
            bank = itemView.findViewById(R.id.txtAnyBank);
            account = itemView.findViewById(R.id.txt_acc_number);
            type = itemView.findViewById(R.id.txt_acc_type);
            ifsc1 = itemView.findViewById(R.id.txt_ifsc_number);
            branch = itemView.findViewById(R.id.txt_bank_location);
            address = itemView.findViewById(R.id.txt_bank_address);
            pan1 = itemView.findViewById(R.id.txt_pan_card_number);
            TextView gst = itemView.findViewById(R.id.txt_gst_number);
        }
    }
}
