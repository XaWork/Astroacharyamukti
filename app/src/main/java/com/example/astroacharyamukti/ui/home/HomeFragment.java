package com.example.astroacharyamukti.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.activity.HomeActivity;
import com.example.astroacharyamukti.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        Button btn_submit=root.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void dialog(){
        Dialog dialog=new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_online_layout);
        Button button_online = dialog.findViewById(R.id.button_online);
        button_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.cancel();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        dialog();
    }
}