    package com.example.uicomponente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RoomInfoFragment extends DialogFragment {
    private static final String ARG_ROOM_NAME = "ROOM_NAME";

    public static RoomInfoFragment newInstance(String roomName) {
        RoomInfoFragment fragment = new RoomInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROOM_NAME, roomName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_info, container, false);

        String roomName = getArguments().getString(ARG_ROOM_NAME, "Ambiente");
        TextView titleView = view.findViewById(R.id.roomTitle);
        titleView.setText(roomName);

        TextView descriptionView = view.findViewById(R.id.roomDescription);
        String description = getRoomDescription(roomName);
        descriptionView.setText(description);

        return view;
    }

    private String getRoomDescription(String roomName) {
        switch (roomName) {
            case "Room 1":
                return "Descripción del Room 1";
            case "Room 2":
                return "Descripción del Room 2";
            case "Room 3":
                return "Descripción del Room 3";
            case "Patio":
                return "Descripción del Patio";
            default:
                return "Información no disponible";
        }
    }
}
