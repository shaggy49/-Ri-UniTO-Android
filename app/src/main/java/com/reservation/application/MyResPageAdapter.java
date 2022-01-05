package com.reservation.application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.reservation.application.fragments.myreservations.CancelledFragment;
import com.reservation.application.fragments.myreservations.CompletedFragment;
import com.reservation.application.fragments.myreservations.TodosFragment;

public class MyResPageAdapter extends FragmentPagerAdapter {

    private int nOfTabs = 3;

    public MyResPageAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.nOfTabs = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TodosFragment();
            case 1:
                return new CompletedFragment();
            case 2:
                return new CancelledFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nOfTabs;
    }
}
