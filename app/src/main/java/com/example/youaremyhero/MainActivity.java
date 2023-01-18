package com.example.youaremyhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    fragment_diary fragmentDiary;
    fragment_graph fragmentGraph;
    fragment_calender fragmentCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCalender = new fragment_calender();
        fragmentGraph = new fragment_graph();
        fragmentDiary = new fragment_diary();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentCalender).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nevigation);
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
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentGraph).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }
}