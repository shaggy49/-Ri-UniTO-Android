package com.reservation.application.fragments.myreservations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.reservation.application.R;
import com.reservation.application.dto.ReservationRequestedDTO;

import java.util.List;

public class MyReservationAdapter extends BaseAdapter {
    private List<ReservationRequestedDTO> rReservations = null;
    private Context context = null;

    public MyReservationAdapter(Context context, List<ReservationRequestedDTO> rReservations) {
        this.context = context;
        this.rReservations = rReservations;
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
            view= LayoutInflater.from(context).inflate(R.layout.my_reservation_item, null);
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
        return view;
    }
}
