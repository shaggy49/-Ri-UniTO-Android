package com.reservation.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.reservation.application.fragments.myreservations.MyResPageAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MyReservationActivity extends AppCompatActivity implements Callback {

    TabLayout tabLayout;
    TabItem reservationTodos;
    TabItem reservationCompleted;
    TabItem reservationCancelled;
    ViewPager viewPager;
    private String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        tabLayout = findViewById(R.id.mie_prenotazioni_tabs);
        reservationTodos = findViewById(R.id.reservation_todos);
        reservationCompleted = findViewById(R.id.reservation_completed);
        reservationCancelled = findViewById(R.id.reservation_cancelled);
        viewPager = findViewById(R.id.mie_prenotazioni_ViewPager);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cookie = extras.getString("cookie");
        }

        MyResPageAdapter myResPageAdapter = new MyResPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), cookie);

        viewPager.setAdapter(myResPageAdapter);

        tabLayout.setupWithViewPager(viewPager);

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
                        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                        mainActivityIntent.putExtra("cookie", cookie);
                        mainActivityIntent.putExtra("fromMyResActivity", true);
                        startActivity(mainActivityIntent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if(response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            String body = responseBody.string();
            runOnUiThread(() -> {
                Toast.makeText(this, body.trim(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}