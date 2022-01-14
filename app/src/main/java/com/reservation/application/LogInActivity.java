package com.reservation.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.reservation.application.R;

import okhttp3.OkHttpClient;

public class LogInActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    TextView email;
    TextView password;
    Button logInButton;
    Button cancelSigninButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        email = (TextView) findViewById(R.id.insertEmail);
        password = (TextView) findViewById(R.id.insertPassword);
        logInButton = (Button) findViewById(R.id.logInButton);
        cancelSigninButton = (Button) findViewById(R.id.cancelLogInButton);

        /*
        * TODO: cose da fare la prossima volta :
        *   + spostare quanto fatto qui sotto in un'activity a parte
        *   + implementare la login POST
        *
        * */

    }

}