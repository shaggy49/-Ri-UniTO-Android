package com.reservation.application;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.reservation.application.ui.main.SectionsPagerAdapter;
import com.reservation.application.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(findViewById(R.id.mainToolbar));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavigationView);

        bottomNavView.setSelectedItemId(R.id.prenota);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.le_mie_prenotazioni:
                        startActivity(new Intent(getApplicationContext(), MyReservationActivity.class));
                        overridePendingTransition(0,0);
                        Toast.makeText(getApplicationContext(), "Hai cliccato su le mie prenotazioni", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.prenota:
                        Toast.makeText(getApplicationContext(), "Hai cliccato su prenota", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(i);
        return true;
    }
}