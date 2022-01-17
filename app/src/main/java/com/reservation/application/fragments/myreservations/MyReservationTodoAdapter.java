package com.reservation.application.fragments.myreservations;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.reservation.application.R;
import com.reservation.application.dto.ReservationAvailableDTO;
import com.reservation.application.dto.ReservationRequestedDTO;

import java.util.List;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyReservationTodoAdapter extends BaseAdapter {
    private List<ReservationRequestedDTO> rReservations = null;
    private Context context = null;
    private final String cookie;

    public MyReservationTodoAdapter(Context context, List<ReservationRequestedDTO> rReservations, String cookie) {
        this.context = context;
        this.rReservations = rReservations;
        this.cookie = cookie;
    }

    @Override
    public int getCount() {
        return rReservations.size();
    }

    @Override
    public Object getItem(int i) {
        return rReservations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.my_reservation_todo_item, null);
        }
        ReservationRequestedDTO item =(ReservationRequestedDTO) getItem(i);
        TextView txt = view.findViewById(R.id.my_course_item);
        txt.setText(item.getCourse());
        txt=view.findViewById(R.id.my_teacher_item);
        txt.setText(item.getTeacher());
        txt=view.findViewById(R.id.my_date_item);
        txt.setText(item.getDate());
        txt=view.findViewById(R.id.my_time_item);
        txt.setText(item.getTime());
        ImageView checkIcon = view.findViewById(R.id.check_done_icon);
        checkIcon.setOnClickListener(v -> {
            confirmOperation(v, item, "completed");
        });
        ImageView deleteIcon = view.findViewById(R.id.delete_icon);
        deleteIcon.setOnClickListener(v -> {
            confirmOperation(v, item, "deleted");
        });
        return view;
    }

    private void confirmOperation(View view, ReservationRequestedDTO item, String status) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        if(status.equals("completed")) {
            alertDialogBuilder.setTitle("Completamento prenotazione");
            alertDialogBuilder.setMessage("Sei sicuro di segnare la lezione come completata?");
            alertDialogBuilder.setIcon(R.drawable.confirm);
        }
        else {
            alertDialogBuilder.setTitle("Cancellazione prenotazione");
            alertDialogBuilder.setMessage("Sei sicuro di voler cancellare la prenotazione?");
            alertDialogBuilder.setIcon(R.drawable.remove_res);
        }
        alertDialogBuilder.setPositiveButton("Si", (dialogInterface, i) -> {
            setReservation(item, status);
            rReservations.remove(item);
            notifyDataSetChanged();
        });
        alertDialogBuilder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void setReservation(ReservationRequestedDTO reservation, String status) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    final Request original = chain.request();
                    final Request authorized = original.newBuilder()
                            .addHeader("Cookie", cookie)
                            .build();
                    return chain.proceed(authorized);
                })
                .build();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("reservationapplication.herokuapp.com")
                .addPathSegment("requested-reservations")
                .addQueryParameter("idRequestedReservation", String.valueOf(reservation.getId()))
                .addQueryParameter("status", status)
                .build();

        Log.i("URL FOR BOOK RESERVATION: ", httpUrl.toString());

        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .put(RequestBody.create(null, new byte[]{}))
                .build();

        client.newCall(request).enqueue((Callback) context);
    }
}
