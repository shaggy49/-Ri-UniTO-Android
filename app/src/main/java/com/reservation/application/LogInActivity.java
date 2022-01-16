package com.reservation.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.reservation.application.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

        logInButton.setOnClickListener(view -> {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("email", email.getText().toString())
                    .add("password", password.getText().toString())
                    .build();

            Request request = new Request.Builder()
                    .url("https://reservationapplication.herokuapp.com/log-in")
                    .post(formBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> {
                        popupMessage("Errore", "Server non disponibile al momento");
                    });
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if(response.isSuccessful()) {
                        String responseBody = response.body().string();
                        List<String> Cookielist = response.headers().values("Set-Cookie");
                        String jsessionid = (Cookielist .get(0).split(";"))[0];
                        Log.i("COOKIE FROM LOGIN", jsessionid);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("cookie", jsessionid);
                        i.putExtra("email", email.getText().toString());
                        startActivity(i);
                    }
                    else {
                        runOnUiThread(() -> {
                            popupMessage("Errore", "Utente non registrato");
                        });
                    }
                }
            });
        });

        cancelSigninButton.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });

    }

    public void popupMessage(String title, String errorMessage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errorMessage);
        alertDialogBuilder.setIcon(R.drawable.ic_error);
        alertDialogBuilder.setTitle(title);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}