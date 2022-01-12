package com.reservation.application.fragments.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.reservation.application.R;
import com.reservation.application.ReservationAvailableAdapter;
import com.reservation.application.dto.ReservationAvailableDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MondayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MondayFragment extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MondayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MondayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MondayFragment newInstance(String param1, String param2) {
        MondayFragment fragment = new MondayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_monday, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        List<ReservationAvailableDTO> reservationsAvailable = new ArrayList<ReservationAvailableDTO>(){{
            add(new ReservationAvailableDTO("Programmazione III", "Mario Rossi", "15:00"));
            add(new ReservationAvailableDTO("Tecnologie Web", "Mario Rossi", "15:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "15:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "15:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "16:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "16:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "16:00"));
            add(new ReservationAvailableDTO("Programmazione I", "Mario Rossi", "16:00"));
            add(new ReservationAvailableDTO("Logica", "Mario Rossi", "16:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "17:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "17:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "17:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "17:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "18:00"));
            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "18:00"));
        }};

        ReservationAvailableAdapter adapter = new ReservationAvailableAdapter(getActivity(), reservationsAvailable);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Toast.makeText(getActivity(), "You clicked item n° " + v.getId(), Toast.LENGTH_SHORT).show();
    }
}