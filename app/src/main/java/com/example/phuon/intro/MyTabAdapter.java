package com.example.phuon.intro;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyTabAdapter extends FragmentStatePagerAdapter {

    private String listTab[] = {"booqee", "MVYoutube", "Timetable", "Weather"};
    private FragmentQoobee fragmentBooqee;
    private FragmentYoutube fragmentMV;
    private FragmentTimeTable fragmentTimeTable;
    private FragmentWeather fragmentWeather;

    public MyTabAdapter(FragmentManager fm) {
        super(fm);
        fragmentBooqee = new FragmentQoobee();
        fragmentMV = new FragmentYoutube();
        fragmentTimeTable = new FragmentTimeTable();
        fragmentWeather = new FragmentWeather();
    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0){
            return fragmentBooqee;
        }
        else if (i == 1){
            return fragmentMV;
        }
        else if (i == 2){
            return fragmentTimeTable;
        }
        else if(i == 3){
            return fragmentWeather;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
