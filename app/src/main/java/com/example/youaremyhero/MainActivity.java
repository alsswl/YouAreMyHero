package com.example.youaremyhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener {

    private static final String TAG = "MainActivity";

    Toolbar toolbar;

    Fragment_diary fragmentDiary;
    Fragment_memo fragmentMemo;
    Fragment_calender fragmentCalender;
    Fragment_diary_add fragmentDiaryAdd;
    Fragment_memo_add fragmentMemoAdd;
    BottomNavigationView bottomNavigationView;
    public static DiaryDatabase mDatabase = null;
    public static MemoDatabase kDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCalender = new Fragment_calender();
        fragmentMemo = new Fragment_memo();
        fragmentDiary = new Fragment_diary();
        fragmentDiaryAdd = new Fragment_diary_add();
        fragmentMemo = new Fragment_memo();
        fragmentMemoAdd = new Fragment_memo_add();


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

        Log.d(TAG, "Note database is open.");
        openDatabase();
    }

    protected void onDestroy() {
        super.onDestroy();

        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }

        if (kDatabase != null) {
            kDatabase.close();
            kDatabase = null;
        }
    }

    public void openDatabase() {
        // open database
        if (mDatabase != null) {

            mDatabase.close();
            mDatabase = null;
        }

        mDatabase = DiaryDatabase.getInstance(this);
        boolean isOpen = mDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }

        if (kDatabase != null) {

            kDatabase.close();
            kDatabase = null;
        }

        kDatabase = MemoDatabase.getInstance(this);
        boolean isOpenn = kDatabase.open();
        if (isOpenn) {
            Log.d(TAG, "Memo database is open.");
        } else {
            Log.d(TAG, "Memo database is not open.");
        }
    }

    public void showFragment2(Diary item) {

        fragmentDiaryAdd = new Fragment_diary_add();
        fragmentDiaryAdd.setItem(item);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentDiaryAdd).commit();

    }

    public void showMemoAdd(Memo item) {

        fragmentMemoAdd = new Fragment_memo_add();
        fragmentMemoAdd.setItem(item);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentMemoAdd).commit();

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
        else if(position == 4){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentDiaryAdd).commit();
        }
        else  {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentMemoAdd).commit();
        }
    }
}