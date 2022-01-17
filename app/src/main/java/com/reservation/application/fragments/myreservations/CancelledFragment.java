package com.reservation.application.fragments.myreservations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reservation.application.R;
import com.reservation.application.dto.ReservationRequestedDTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import entities.ReservationRequested;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CancelledFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CancelledFragment extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "cookie";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String cookie;
    private String mParam2;

    public CancelledFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CancelledFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CancelledFragment newInstance(String param1, String param2) {
        CancelledFragment fragment = new CancelledFragment();
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
            cookie = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cancelled, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    final Request original = chain.request();
                    final Request authorized = original.newBuilder()
                            .addHeader("Cookie", cookie)
                            .build();
                    return chain.proceed(authorized);
                })
                .build();

        Request request = new Request.Builder()
                .url(" https://reservationapplication.herokuapp.com/user-reservations")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                //TODO: stampare popup di errore
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Gson gson = new Gson();
                    Type listOfReqResObject = new TypeToken<ArrayList<ReservationRequested>>() {}.getType();
                    List<ReservationRequested> outputList = gson.fromJson(body, listOfReqResObject);
//                    Log.i("REQUESTED RESERVATIONS: ", outputList.toString());
                    requireActivity().runOnUiThread(() -> {
                        List<ReservationRequestedDTO> todoReservations = new ArrayList<>();

                        for (ReservationRequested reservation : outputList) {
                            if(reservation.getStatus().equals("deleted")) {
                                ReservationRequestedDTO todoReservation = new ReservationRequestedDTO(reservation.getId(), reservation.getCourse().getTitle(), reservation.getTeacher().getName() + " " + reservation.getTeacher().getSurname(), reservation.getrDate(), reservation.getrTime());
                                todoReservations.add(todoReservation);
                            }
                        }

                        MyReservationAdapter adapter = new MyReservationAdapter(getActivity(), todoReservations);

                        setListAdapter(adapter);
                    });
                }
            }
        });

    }
}