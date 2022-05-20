package com.example.astroacharyamukti.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.databinding.ActivityHomeActivityBinding;
import com.google.android.material.navigation.NavigationView;




public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHomeActivity.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_actvity);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_actvity, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_actvity);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_appointments:
//                Intent intent = new Intent(getApplicationContext(), BookAppointments.class);
//                startActivity(intent);
                break;
            case R.id.nav_language:
                Intent intent1 = new Intent(getApplicationContext(), LanguageActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_privacy_policy:
                Intent intent2 = new Intent(this, PrivacyPolicy.class);
                startActivity(intent2);
                break;
            case R.id.nav_my_earning:
                Intent earning = new Intent(this, MyEarningActivity.class);
                startActivity(earning);
                break;
            case R.id.nav_slideshow:
                showDialog();
                break;
            case R.id.nav_performance:
                performanceDialog();
                break;
            case R.id.nav_help_support:
                fqeDialog();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_my_account);
        TextView custom_cust_review = dialog.findViewById(R.id.custom_cust_review);
        custom_cust_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    private void performanceDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_performance_layout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    private void fqeDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_help_and_support);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}