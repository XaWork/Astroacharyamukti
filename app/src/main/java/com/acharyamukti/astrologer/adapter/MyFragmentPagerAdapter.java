package com.acharyamukti.astrologer.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.acharyamukti.astrologer.ui.fragment.AboutFragment;
import com.acharyamukti.astrologer.ui.fragment.ChatFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    FragmentManager fm;
    Context context;
    int tabCount;


    public MyFragmentPagerAdapter(Context context, FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
        this.context = context;
        this.fm = supportFragmentManager;
        this.tabCount = tabCount;
    }


    @Override
    public int getCount() {
        return tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AboutFragment();

            case 1:
                return new ChatFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Call";
        else if (position == 1)
            title = "Chat";
        return title;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
