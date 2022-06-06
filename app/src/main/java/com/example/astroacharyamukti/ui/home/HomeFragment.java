package com.example.astroacharyamukti.ui.home;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.activity.UserProfile;
import com.example.astroacharyamukti.databinding.FragmentHomeBinding;
import com.example.astroacharyamukti.helper.Backend;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    TextView name, position, charge;
    CircleImageView imageView;
    String userName, profileImage, userPosition, callCharge, emailId, userNumber;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        name = root.findViewById(R.id.name_home_profile);
        position = root.findViewById(R.id.text_position);
        charge = root.findViewById(R.id.text_charge);
        imageView = root.findViewById(R.id.image_circular);
        imageView.setOnClickListener(this);
        String newPrice = Backend.getInstance(getActivity()).newPrice();
        charge.setText(newPrice);
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        getUser();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getUser() {
        progressBar.setVisibility(View.INVISIBLE);
        String userId = Backend.getInstance(getActivity()).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/profile.php?acharid=%s";
        String dataUrl = String.format(url, userId);
        RequestQueue request = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, dataUrl, response -> {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                jsonObject.getString("status");
                jsonObject.getString("reg_id");
                callCharge = jsonObject.getString("charge");
                userPosition = jsonObject.getString("position");
                profileImage = jsonObject.getString("image");
                emailId = jsonObject.getString("email");
                userNumber = jsonObject.getString("mobile");
                userName = jsonObject.getString("name");
                String msg = jsonObject.getString("msg");
                if (jsonObject.getString("status").equals("true")) {
                    name.setText(userName);
                    position.setText(userPosition);
                    charge.setText(callCharge);
                    String url1 = "https://theacharyamukti.com/image/astro/" + profileImage;
                    Glide.with(getActivity()).load(url1).into(imageView);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(getActivity(), jsonObject.getString("status"), Toast.LENGTH_SHORT).show();
                }
                Backend.getInstance(getActivity()).saveMobile(userNumber);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                return params;
            }
        };
        request.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), UserProfile.class);
        startActivity(intent);
    }
}

