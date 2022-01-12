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

        /*cancelSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "onClick: bottone cliccato");
//                Toast.makeText(getApplicationContext(), "Email: " + email.getText().toString() + ", Pw: " + password.getText().toString(), Toast.LENGTH_SHORT).show();
                Request request = new Request.Builder()
                        .url(" http://10.0.2.2:8080/reservation_application_war_exploded/available-reservations")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            ResponseBody responseBody = response.body();
                            String body = responseBody.string();
                            Log.i(TAG, "onResponse (raw): " + body);
                            Gson gson = new Gson();
                            Type listOfAvResObject = new TypeToken<ArrayList<ReservationAvailable>>() {}.getType();
                            List<ReservationAvailable> outputList = gson.fromJson(body, listOfAvResObject);
                            Log.i(TAG, "onResponse (gson): " + outputList);
                        }
                    }
                });
            }
        });*/
    }

}