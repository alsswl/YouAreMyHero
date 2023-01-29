package com.example.youaremyhero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_diary extends Fragment {



    RecyclerView recyclerView;
    DiaryAdapter adapter;

    Context context;
    OnTabItemSelectedListener listener;

    public void onAttach(Context context){
        super.onAttach(context);

        this.context = context;

        if(context instanceof OnTabItemSelectedListener){
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override public void onDetach(){
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_diary,container,false);

        initUI(rootView);
        loadNoteListData();
        return rootView;
    }

    @SuppressLint("NotifyDataSetChanged")
    public int loadNoteListData(){
        AppConstants.println("loadNoteListData called.");
        String sql = "select _id, PLACE, PARENTCONTENTS, MOOD, BABYCONTENTS, NEWONE, THEME, CREATEDATESTR from "+ DiaryDatabase.TABLE_NOTE ;

        int recordCount = -1;
        DiaryDatabase database = DiaryDatabase.getInstance(context);
        if(database != null){
            Cursor outCursor = database.rawQuery(sql);

            recordCount = outCursor.getCount();
            AppConstants.println("record count : "+recordCount+"\n");

            ArrayList<Diary> items = new ArrayList<Diary>();

            for(int i = 0;i<recordCount;i++){

                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String place = outCursor.getString(1);
                String parentcontents = outCursor.getString(2);
                String mood = outCursor.getString(3);
                String babycontents = outCursor.getString(4);
                String newone = outCursor.getString(5);
                String theme = outCursor.getString(6);
                String date = outCursor.getString(7);

                items.add(new Diary(_id,place,parentcontents,mood,babycontents,newone,theme,date));

            }

            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();

        }
        return recordCount;
    }


    private void initUI(ViewGroup rootView){

        ImageButton add = rootView.findViewById(R.id.add);
        add.setOnClickListener(view -> {
            if(listener != null){
                listener.onTabSelected(4);
            }

        });

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DiaryAdapter();

        adapter.addItem(new Diary(0,"집","이거랑 저거랑 이러랑 저거랑 이거랑저거랑 이거랑 저거랑 이러갈 ","0","좋았다","처음 걸었다","이거랑 저거랑 이러랑 저거랑 이거랑저거랑 이거랑 저거랑 이러갈",
                "2월 2일"));
        adapter.addItem(new Diary(1,"집","좋았다","1","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(2,"집","좋았다","2","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(3,"집","좋았다","4","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(4,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(5,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(6,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(7,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(8,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new Diary(9,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnDiaryItemClickListener() {
            @Override
            public void onItemClick(DiaryAdapter.ViewHolder holder, View view, int position) {
                Diary item = adapter.getItem(position);
                Toast.makeText(getContext(),"아이템선택됨:" + item.getTheme(),Toast.LENGTH_LONG).show();

                if (listener != null) {
                    listener.showFragment2(item);
                }
            }
        });
    }
}