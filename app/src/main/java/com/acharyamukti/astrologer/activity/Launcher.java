package com.acharyamukti.astrologer.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.astrologer.api.RetrofitClient;
import com.acharyamukti.astrologer.helper.Backend;
import com.acharyamukti.astrologer.model.AppVersionModel;
import com.bumptech.glide.Glide;
import com.example.astroacharyamukti.BuildConfig;
import com.example.astroacharyamukti.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        ImageView image=findViewById(R.id.imageApp);
        Glide.with(this).load(R.drawable.logo5).into(image);

          /*
        First we have to check the version of our app,
        IF play store version and build version is same then user can move forward to next screen,
        other wise send user to play store to update the app
        */

        moveToNextScreen();
    }

    private void getAppVersion() {
        Call<AppVersionModel> call = RetrofitClient.getInstance().getApi().getAppVersion();

        call.enqueue(new Callback<AppVersionModel>() {
            @Override
            public void onResponse(@NonNull Call<AppVersionModel> call, @NonNull Response<AppVersionModel> response) {
                AppVersionModel appVersionModel = response.body();

                if (response.isSuccessful()) {
                    assert appVersionModel != null;
                    checkAppVersion(appVersionModel.getAstrologer_app_version());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AppVersionModel> call, @NonNull Throwable t) {
                Toast.makeText(Launcher.this, t.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void checkAppVersion(String astro_app_version) {
        String buildVersion = BuildConfig.VERSION_NAME;

        Log.e("Launcher", "checkAppVersion: User app version " + astro_app_version + "\nBuild Version : " + buildVersion);

        if (Objects.equals(astro_app_version, buildVersion)) {
            moveToNextScreen();
        } else {
            moveToPlayStore();
        }
    }

    private void moveToPlayStore() {
        Toast.makeText(Launcher.this, "Update your app.", Toast.LENGTH_SHORT).show();

        String appPackageName = getPackageName();
        Log.e("Launcher", "Package Name : "+appPackageName);

        try {
            startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + appPackageName)
                    )
            );
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)
                    )
            );
        }
    }

    private void moveToNextScreen() {
        String userName = Backend.getInstance(this).getUserName();
        String password = Backend.getInstance(this).getPassword();
        new Timer().schedule(new TimerTask() {
            public void run() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String storedUsername = prefs.getString("username", userName);
                String storedPassword = prefs.getString("password", password);
                if (storedUsername.equalsIgnoreCase(userName)&&storedPassword.equalsIgnoreCase(password)) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000);
    }
}