package com.example.astroacharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.model.ReviewModel;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<ReviewModel> reviewModels;

    public ReviewAdapter(Context context, List<ReviewModel> reviewModels) {
        this.context = context;
        this.reviewModels = reviewModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_review_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewModel review=reviewModels.get(position);
        holder.call_id.setText(review.getCall_id());
        holder.name.setText(review.getName());
        holder.date.setText(review.getDate());
        holder.comment.setText(review.getComment());
        holder.rating.setText(review.getRating());
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView call_id, name, comment, rating, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            call_id = itemView.findViewById(R.id.txt_call_id);
            name = itemView.findViewById(R.id.review_name);
            comment = itemView.findViewById(R.id.giving_comment);
            rating = itemView.findViewById(R.id.ratingStar);
            date = itemView.findViewById(R.id.date_time_review);
        }
    }
}
