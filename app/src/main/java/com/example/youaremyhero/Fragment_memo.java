package com.example.youaremyhero;

import android.content.Context;
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

public class Fragment_memo extends Fragment {
    RecyclerView recyclerView;
    MemoAdapter adapter;

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

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_memo,container,false);

        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView){

        ImageButton add = rootView.findViewById(R.id.add);
        add.setOnClickListener(view -> {
            if(listener != null){
                listener.onTabSelected(5);
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MemoAdapter();

        adapter.addItem(new Memo(0,"학교 준비물","교과서 연필 지우개 담요 방석"));
        adapter.addItem(new Memo(1,"학교 준비물","교과서 연필 지우개 담요 방석"));
        adapter.addItem(new Memo(2,"학교 준비물","교과서 연필 지우개 담요 방석"));
        adapter.addItem(new Memo(3,"학교 준비물","교과서 연필 지우개 담요 방석"));
        adapter.addItem(new Memo(4,"학교 준비물","교과서 연필 지우개 담요 방석"));
        adapter.addItem(new Memo(5,"학교 준비물","교과서 연필 지우개 담요 방석"));
        adapter.addItem(new Memo(6,"학교 준비물","교과서 연필 지우개 담요 방석"));



        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnMemoItemClickListener() {
            @Override
            public void onItemClick(MemoAdapter.ViewHolder holder, View view, int position) {
                Memo item = adapter.getItem(position);
                Toast.makeText(getContext(),"아이템선택됨:" + item.getTheme(),Toast.LENGTH_LONG).show();
            }

        });
    }
}