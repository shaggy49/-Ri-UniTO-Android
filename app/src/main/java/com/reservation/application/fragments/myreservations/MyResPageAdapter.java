package com.reservation.application.fragments.myreservations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyResPageAdapter extends FragmentPagerAdapter {

    private int nOfTabs = 3;
    private String[] tabNames = {"Da fare", "Completate", "Cancellate"};
    private final String cookie;


    public MyResPageAdapter(@NonNull FragmentManager fm, int tabCount, String cookie) {
        super(fm);
        this.nOfTabs = tabCount;
        this.cookie = cookie;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("cookie", cookie);

        switch (position) {
            case 0:
                TodosFragment todosFragment = new TodosFragment();
                todosFragment.setArguments(bundle);
                return todosFragment;
            case 1:
                CompletedFragment completedFragment = new CompletedFragment();
                completedFragment.setArguments(bundle);
                return completedFragment;
            case 2:
                CancelledFragment cancelledFragment = new CancelledFragment();
                cancelledFragment.setArguments(bundle);
                return cancelledFragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    @Override
    public int getCount() {
        return nOfTabs;
    }
}
