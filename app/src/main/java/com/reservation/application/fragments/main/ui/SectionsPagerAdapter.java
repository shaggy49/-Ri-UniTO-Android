package com.reservation.application.fragments.main.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.reservation.application.fragments.main.FridayFragment;
import com.reservation.application.fragments.main.MondayFragment;
import com.reservation.application.R;
import com.reservation.application.fragments.main.ThursdayFragment;
import com.reservation.application.fragments.main.TuesdayFragment;
import com.reservation.application.fragments.main.WednesdayFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5};
    private final Context mContext;
    private final String cookie;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String cookie) {
        super(fm);
        mContext = context;
        this.cookie = cookie;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position + 1);
        Bundle bundle = new Bundle();
        bundle.putString("cookie", cookie);
        switch (position) {
            case 0:
                MondayFragment mondayFragment = new MondayFragment();
                mondayFragment.setArguments(bundle);
                return mondayFragment;
            case 1 :
                TuesdayFragment tuesdayFragment = new TuesdayFragment();
                tuesdayFragment.setArguments(bundle);
                return tuesdayFragment;
            case 2 :
                WednesdayFragment wednesdayFragment = new WednesdayFragment();
                wednesdayFragment.setArguments(bundle);
                return wednesdayFragment;
            case 3 :
                ThursdayFragment thursdayFragment = new ThursdayFragment();
                thursdayFragment.setArguments(bundle);
                return thursdayFragment;
            case 4 :
                FridayFragment fridayFragment = new FridayFragment();
                fridayFragment.setArguments(bundle);
                return fridayFragment;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 5;
    }
}