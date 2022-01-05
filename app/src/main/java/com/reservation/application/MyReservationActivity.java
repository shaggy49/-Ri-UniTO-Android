package com.reservation.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyReservationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem reservationTodos;
    TabItem reservationCompleted;
    TabItem reservationCancelled;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        tabLayout = findViewById(R.id.mie_prenotazioni_tabs);
        reservationTodos = findViewById(R.id.reservation_todos);
        reservationCompleted = findViewById(R.id.reservation_completed);
        reservationCancelled = findViewById(R.id.reservation_cancelled);
        viewPager = findViewById(R.id.mie_prenotazioni_ViewPager);

        MyResPageAdapter myResPageAdapter = new MyResPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(myResPageAdapter);

//        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavigationView);

        bottomNavView.setSelectedItemId(R.id.le_mie_prenotazioni);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.le_mie_prenotazioni:
                        return true;
                    case R.id.prenota:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}