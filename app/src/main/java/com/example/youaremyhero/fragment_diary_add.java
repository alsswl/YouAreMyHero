package com.example.youaremyhero;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.location.GnssAntennaInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Date;


public class fragment_diary_add extends Fragment {


    Context context;
    onTabItemSelectedListener listener;
    TextView dateText;
    DatePickerDialog datePickerDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        if(context instanceof onTabItemSelectedListener){
            listener = (onTabItemSelectedListener) context;
        }
    }

    public void onDetach(){
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_diary_add, container, false);
        hideBottomNavigation(true);
        dateText = v.findViewById(R.id.dateText);
        ImageButton datepickBtn = (ImageButton)v.findViewById(R.id.calendarButton);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateText.setText(year + "년 " + (month+1) + "월 " + dayOfMonth + "일");
            }
        };

        datepickBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                Date currentDate = new Date();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });

        initUI(v);
        return v;
    }

    private void initUI(ViewGroup v){

        Button saveButton = v.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onTabSelected(1);
                    hideBottomNavigation(false);
                }
            }
        });

        Button deleteButton = v.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onTabSelected(1);
                    hideBottomNavigation(false);
                }
            }
        });

        Button backButton = v.findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onTabSelected(1);
                    hideBottomNavigation(false);
                }
            }
        });
    }



    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottom_nevigation);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }



}