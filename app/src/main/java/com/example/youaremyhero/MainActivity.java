package com.example.youaremyhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements onTabItemSelectedListener{
    Toolbar toolbar;

    fragment_diary fragmentDiary;
    fragment_memo fragmentMemo;
    fragment_calender fragmentCalender;

    fragment_diary_add fragmentDiaryAdd;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCalender = new fragment_calender();
        fragmentMemo = new fragment_memo();
        fragmentDiary = new fragment_diary();
        fragmentDiaryAdd = new fragment_diary_add();


        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentCalender).commit();
        bottomNavigationView = findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.tab1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentCalender).commit();
                                return true;
                            case R.id.tab2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentDiary).commit();
                                return true;
                            case R.id.tab3:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentMemo).commit();
                                return true;

                        }
                        return false;
                    }
                }
        );
    }



    @Override
    public void onTabSelected(int position) {
        if(position == 0){
            bottomNavigationView.setSelectedItemId(R.id.tab1);
        }
        else if(position == 1){
            bottomNavigationView.setSelectedItemId(R.id.tab2);
        }
        else if(position == 2){
            bottomNavigationView.setSelectedItemId(R.id.tab3);
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentDiaryAdd).commit();
        }
    }
}