package com.reservation.application.fragments.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reservation.application.R;
import com.reservation.application.dto.ReservationAvailableDTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import entities.ReservationAvailable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(" https://reservationapplication.herokuapp.com/available-reservations")
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
                    Gson gson = new Gson();
                    Type listOfAvResObject = new TypeToken<ArrayList<ReservationAvailable>>() {}.getType();
                    List<ReservationAvailable> outputList = gson.fromJson(body, listOfAvResObject);
//                    Log.i("INFO", outputList.toString());
                    getActivity().runOnUiThread(() -> {
//                        Toast.makeText(getActivity(), outputList.toString(), Toast.LENGTH_LONG).show();
//                        List<ReservationAvailableDTO> reservationsAvailable = new ArrayList<ReservationAvailableDTO>(){{
//                            add(new ReservationAvailableDTO(outputList.get(0).getCourse().getTitle(), outputList.get(0).getTeacher().getName() + " " + outputList.get(0).getTeacher().getSurname(), outputList.get(0).getTime() + ":00"));
//                        }};

                        List<ReservationAvailableDTO> mondayReservations = new ArrayList<>();

                        for (ReservationAvailable reservation : outputList) {
                            if(reservation.getDate().equals("lun")) {
                                mondayReservations.add(new ReservationAvailableDTO(reservation.getCourse().getTitle(), reservation.getTeacher().getName() + " " + reservation.getTeacher().getSurname(), reservation.getTime() + ":00"));
                            }
                        }

                        ReservationAvailableAdapter adapter = new ReservationAvailableAdapter(getActivity(), mondayReservations);

                        setListAdapter(adapter);
                    });
                }
            }
        });

//        List<ReservationAvailableDTO> reservationsAvailable = new ArrayList<ReservationAvailableDTO>(){{
//            add(new ReservationAvailableDTO("Programmazione III", "Mario Rossi", "15:00"));
//            add(new ReservationAvailableDTO("Tecnologie Web", "Mario Rossi", "15:00"));
//            add(new ReservationAvailableDTO("Reti I", "Mario Rossi", "15:00"));
//        }};


    }

}