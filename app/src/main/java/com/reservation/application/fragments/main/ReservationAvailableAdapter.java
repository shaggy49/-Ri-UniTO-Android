package com.reservation.application.fragments.main;


import android.content.Context;
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

import java.util.List;

public class ReservationAvailableAdapter extends BaseAdapter {
    private List<ReservationAvailableDTO> aReservations =null;
    private Context context=null;
    private final String cookie;

    public ReservationAvailableAdapter(Context context, List<ReservationAvailableDTO> aReservations, String cookie)
    {
        this.aReservations = aReservations;
        this.context=context;
        this.cookie = cookie;
    }
    @Override
    public int getCount()
    {
        return aReservations.size();
    }
    @Override
    public Object getItem(int position)
    {
        return aReservations.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View v, ViewGroup vg) {
        if (v==null)
        {
            v= LayoutInflater.from(context).inflate(R.layout.available_resevations_item, null);
        }
        ReservationAvailableDTO item =(ReservationAvailableDTO) getItem(position);
        TextView txt = v.findViewById(R.id.course_item);
        txt.setText(item.getCourse());
        txt=v.findViewById(R.id.teacher_item);
        txt.setText(item.getTeacher());
        txt=v.findViewById(R.id.time_item);
        txt.setText(item.getTime());
        ImageView bookIcon = v.findViewById(R.id.book_icon);
        bookIcon.setOnClickListener(view -> {
            if(cookie != null)
                confirmBookReservation(view, item);
            else
                Toast.makeText(view.getContext(), "Accedi per prenotare una lezione", Toast.LENGTH_SHORT).show();
        });
        return v;
    }

    public void confirmBookReservation(View view, ReservationAvailableDTO reservationSelected){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setMessage("Sei sicuro di voler prenotare la lezione?");
        alertDialogBuilder.setIcon(R.drawable.confirm);
        alertDialogBuilder.setTitle("Conferma la prenotazione");
        alertDialogBuilder.setPositiveButton("Si", (dialogInterface, i) -> dialogInterface.dismiss()); //qui verrà fatta la chiamata PUT (ricordarsi di notificare l'utente di avvenuta prenotazione)
        alertDialogBuilder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}