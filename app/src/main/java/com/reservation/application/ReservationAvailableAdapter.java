package com.reservation.application;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.reservation.application.dto.ReservationAvailableDTO;

import java.util.List;

public class ReservationAvailableAdapter extends BaseAdapter {
    private List<ReservationAvailableDTO> aReservations =null;
    private Context context=null;
    public ReservationAvailableAdapter(Context context,List<ReservationAvailableDTO> aReservations)
    {
        this.aReservations = aReservations;
        this.context=context;
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
    public View getView(int position, View v, ViewGroup vg)
    {
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
        return v;
    }
}