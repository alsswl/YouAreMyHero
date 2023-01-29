package com.example.youaremyhero;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.location.GnssAntennaInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.github.channguyen.rsv.RangeSliderView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Date;


public class fragment_diary_add extends Fragment {

    private static final String TAG = "fragment_diary_add";

    int mMode = AppConstants.MODE_INSERT;
    int _id = 1;

    RangeSliderView moodSlider;
    int moodIndex = 2;

    diary item;

    TextView date;
    EditText parentContents;
    EditText babyContents;
    EditText theme;
    EditText place;
    EditText newOne;



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

        applyItem();
        return v;
    }

    public void applyItem() {
        AppConstants.println("applyItem called.");

        if (item != null ) {
            mMode = AppConstants.MODE_MODIFY;
            setTheme(item.getTheme());
            setPlace(item.getPlace());
            setDate(item.getCreateDateStr());
            setParentContents(item.getParentContents());
            setBabyContents(item.getBabyContents());
            setNewOne(item.getNewOne());
            setMood(item.getMood());
        } else {
            mMode = AppConstants.MODE_INSERT;
            setTheme("");
            setPlace("");
            setDate("날짜를 입력하세요");
            setParentContents("");
            setBabyContents("");
            setNewOne("");
            setMood("2");
        }

    }

    public void setTheme(String data) {
        theme.setText(data);
    }

    public void setDate(String data) {
        date.setText(data);
    }

    public void setNewOne(String data) {
        newOne.setText(data);
    }

    public void setParentContents(String data) {
        parentContents.setText(data);
    }

    public void setBabyContents(String data) {
        babyContents.setText(data);
    }

    public void setPlace(String data) {
        place.setText(data);
    }

    public void setMood(String mood) {
        try {
            moodIndex = Integer.parseInt(mood);
            moodSlider.setInitialIndex(moodIndex);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI(ViewGroup v){

        place = v.findViewById(R.id.place);
        parentContents = v.findViewById(R.id.parentContents);
        babyContents = v.findViewById(R.id.babyContents);
        theme = v.findViewById(R.id.theme);
        newOne = v.findViewById(R.id.newOne);
        date = v.findViewById(R.id.dateText);


        Button saveButton = v.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(mMode == AppConstants.MODE_INSERT){
                    saveDiary();
                }else if(mMode == AppConstants.MODE_MODIFY){
                    modifyDiary();
                }
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
                deleteNote();
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

        moodSlider = v.findViewById(R.id.mood);
        final RangeSliderView.OnSlideListener listener = new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                AppConstants.println("moodIndex changed to " + index);
                moodIndex = index;
            }
        };

        moodSlider.setOnSlideListener(listener);
        moodSlider.setInitialIndex(2);
    }
    private void saveDiary(){
        String placeGet = place.getText().toString();
        String parentContentsGet = parentContents.getText().toString();
        String babyContentsGet = babyContents.getText().toString();
        String newOneGet = newOne.getText().toString();
        String dateGet = date.getText().toString();
        String themeGet = theme.getText().toString();

        String sql = "insert into "+DiaryDatabase.TABLE_NOTE +
                "(PLACE, PARENTCONTENTS, MOOD, BABYCONTENTS, NEWONE, THEME, CREATEDATESTR) values("+
                "'"+placeGet+"', " +
                "'"+parentContentsGet+"', " +
                "'"+moodIndex+"', " +
                "'"+babyContentsGet+"', " +
                "'"+newOneGet+"', " +
                "'"+themeGet+"', " +
                "'"+dateGet+"')";
        Log.d(TAG,"sql : " + sql);

        DiaryDatabase database = DiaryDatabase.getInstance(context);
        database.execSQL(sql);



    }

    private void modifyDiary(){
        if(item != null){
            String placeGet = place.getText().toString();
            String parentContentsGet = parentContents.getText().toString();
            String babyContentsGet = babyContents.getText().toString();
            String newOneGet = newOne.getText().toString();
            String dateGet = date.getText().toString();
            String themeGet = theme.getText().toString();

            String sql = "update "+DiaryDatabase.TABLE_NOTE+
                    " set "+
                    "   PLACE = '"+ placeGet +"'"+
                    "   ,PARENTCONTENTS = '"+ parentContentsGet +"'"+
                    "   ,MOOD = '"+ moodIndex +"'"+
                    "   ,BABYCONTENTS = '"+ babyContentsGet +"'"+
                    "   ,NEWONE= '"+ newOneGet +"'"+
                    "   ,THEME = '"+ themeGet +"'"+
                    "   ,CREATEDATESTR = '"+ dateGet +"'"+
                    " where " +
                    " _id = "+ item.id;

            Log.d(TAG,"sql : "+sql);
            DiaryDatabase database = DiaryDatabase.getInstance(context);
            database.execSQL(sql);


        }
    }

    private void deleteNote(){
        AppConstants.println("deleteDiary called");
        if(item != null){
            String sql = "delete from "+ DiaryDatabase.TABLE_NOTE+
                    " where "+
                    " _id = " + item.id;

            Log.d(TAG,"sql : "+sql);
            DiaryDatabase database = DiaryDatabase.getInstance(context);
            database.execSQL(sql);
        }
    }

    public void setItem(diary item) {
        this.item = item;
    }



    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.bottom_nevigation);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }



}