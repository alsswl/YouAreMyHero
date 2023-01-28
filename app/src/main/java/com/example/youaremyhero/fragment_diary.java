package com.example.youaremyhero;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class fragment_diary extends Fragment {

    RecyclerView recyclerView;
    diaryAdapter adapter;

    Context context;
    onTabItemSelectedListener listener;

    public void onAttach(Context context){
        super.onAttach(context);

        this.context = context;

        if(context instanceof onTabItemSelectedListener){
            listener = (onTabItemSelectedListener) context;
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
        return rootView;
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

        adapter = new diaryAdapter();

        adapter.addItem(new diary(0,"집","이거랑 저거랑 이러랑 저거랑 이거랑저거랑 이거랑 저거랑 이러갈 ","0","좋았다","처음 걸었다","이거랑 저거랑 이러랑 저거랑 이거랑저거랑 이거랑 저거랑 이러갈",
                "2월 2일"));
        adapter.addItem(new diary(1,"집","좋았다","1","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(2,"집","좋았다","2","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(3,"집","좋았다","4","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(4,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(5,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(6,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(7,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(8,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));
        adapter.addItem(new diary(9,"집","좋았다","3","좋았다","처음 걸었다","집에 있었던 날",
                "2월 2일"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new onDiaryItemClickListener() {
            @Override
            public void onItemClick(diaryAdapter.ViewHolder holder, View view, int position) {
                diary item = adapter.getItem(position);
                Toast.makeText(getContext(),"아이템선택됨:" + item.getTheme(),Toast.LENGTH_LONG).show();
            }
        });
    }
}