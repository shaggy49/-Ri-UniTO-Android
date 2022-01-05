package com.reservation.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavigationView);

        bottomNavView.setSelectedItemId(R.id.le_mie_prenotazioni);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.le_mie_prenotazioni:
                        Toast.makeText(getApplicationContext(), "Hai cliccato su le mie prenotazioni", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.prenota:
                        Toast.makeText(getApplicationContext(), "Hai cliccato su prenota", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}