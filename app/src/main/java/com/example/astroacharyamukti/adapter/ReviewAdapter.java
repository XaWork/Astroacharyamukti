package com.example.astroacharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.model.ReviewModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<ReviewModel> reviewModels;
    EditText reply;
    ProgressBar dialog;

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
        ReviewModel review = reviewModels.get(position);
        holder.call_id.setText(review.getCall_id());
        holder.name.setText(review.getName());
        holder.date.setText(review.getDate());
        holder.comment.setText(review.getComment());
        holder.replyDate.setText(review.getReply_date());
        reply.setText(review.getReply());
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView call_id, name, comment, date, replyDate;
        RatingBar rating;
        ImageView sendIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            call_id = itemView.findViewById(R.id.txt_call_id);
            name = itemView.findViewById(R.id.review_name);
            comment = itemView.findViewById(R.id.giving_comment);
            rating = itemView.findViewById(R.id.ratingStar);
            date = itemView.findViewById(R.id.date_time_review);
            reply = itemView.findViewById(R.id.text_thank);
            sendIcon = itemView.findViewById(R.id.sendImage);
            replyDate = itemView.findViewById(R.id.replyDate);
            // replyPost = reply.getText().toString();
            dialog = itemView.findViewById(R.id.progressBar);
            dialog.setVisibility(View.INVISIBLE);
            sendIcon.setOnClickListener(view -> {
                ReviewModel reviewModel = reviewModels.get(getAdapterPosition());
                postReview(reviewModel);
            });
        }
    }

    private void postReview(ReviewModel reviewModel) {
        String reviewId = reviewModel.getReview_id();
        String replyNew = reply.getText().toString();
        String url = "https://theacharyamukti.com/managepanel/apis/review-post.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        dialog.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            dialog.setVisibility(View.INVISIBLE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                String msg = jsonObject.getString("msg");
                if (jsonObject.getString("status").equals("true")) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, jsonObject.getString("status"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            dialog.setVisibility(View.VISIBLE);
            Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("review_id", reviewId);
                params.put("reply", replyNew);
                return params;
            }
        };
        requestQueue.add(request);
    }
}
