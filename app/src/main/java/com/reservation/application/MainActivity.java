package com.reservation.application;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.reservation.application.dto.ReservationAvailableDTO;
import com.reservation.application.fragments.main.ui.SectionsPagerAdapter;
import com.reservation.application.databinding.ActivityMainBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity implements Callback{

    private ActivityMainBinding binding;
    private String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(findViewById(R.id.mainToolbar));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cookie = extras.getString("cookie");
            boolean fromMyResActivitY = extras.getBoolean("fromMyResActivity");
            String email = extras.getString("email");
            if(!fromMyResActivitY)
                Toast.makeText(this, "Benvenuto, " + email, Toast.LENGTH_SHORT).show();
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), cookie);
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
                        if(cookie != null) {
                            Intent myReservationIntent = new Intent(getApplicationContext(), MyReservationActivity.class);
                            myReservationIntent.putExtra("cookie", cookie);
                            startActivity(myReservationIntent);
                            overridePendingTransition(0,0);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Accedi per vedere le tue prenotazioni", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    case R.id.prenota:
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

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if(response.isSuccessful()) {
            runOnUiThread(() -> {
                Toast.makeText(this, "Prenotazione effettuata!", Toast.LENGTH_SHORT).show();
            });
        }
        else {
            ResponseBody responseBody = response.body();
            String body = responseBody.string();
            runOnUiThread(() -> {
                Toast.makeText(this, body.trim(), Toast.LENGTH_LONG).show();
            });
        }
    }

}