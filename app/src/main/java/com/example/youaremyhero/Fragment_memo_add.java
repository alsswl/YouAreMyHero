package com.example.youaremyhero;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_memo_add extends Fragment {
    private static final String TAG = "fragment_memo_add";
    Context context;
    Memo item;
    OnTabItemSelectedListener listener;
    int mMode = AppConstants.MODE_INSERT;
    int _id = 1;

    EditText theme;
    EditText contents;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    public void onDetach() {
        super.onDetach();

        if (context != null) {
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_memo_add, container, false);
        hideBottomNavigation(true);


        initUI(v);
        applyItem();
        return v;
    }

    public void applyItem() {
        AppConstants.println("applyItem called.");

        if (item != null) {
            mMode = AppConstants.MODE_MODIFY;
            setTheme(item.getTheme());
            setContents(item.getMemoContent());
        } else {
            mMode = AppConstants.MODE_INSERT;
            setTheme("");
            setContents("");
        }

    }

    public void setTheme(String data) {
        theme.setText(data);
    }

    public void setContents(String data) {
        contents.setText(data);
    }

    private void initUI(ViewGroup v) {
        theme = v.findViewById(R.id.theme);
        contents = v.findViewById(R.id.memoContent);

        Button saveButton = v.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mMode == AppConstants.MODE_INSERT) {
                    saveMemo();
                } else if (mMode == AppConstants.MODE_MODIFY) {
                    modifyMemo();
                }
                if (listener != null) {
                    listener.onTabSelected(2);
                    hideBottomNavigation(false);
                }
            }
        });

        Button deleteButton = v.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
                if (listener != null) {
                    listener.onTabSelected(2);
                    hideBottomNavigation(false);
                }
            }
        });

        Button backButton = v.findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onTabSelected(2);
                    hideBottomNavigation(false);
                }
            }
        });


    }

    private void saveMemo() {
        String themeGet = theme.getText().toString();
        String contentGet = contents.getText().toString();

        String sql = "insert into " + MemoDatabase.TABLE_NOTE +
                "(THEME, CONTENTS) values(" +
                "'" + themeGet + "', " +
                "'" + contentGet + "')";
        Log.d(TAG, "sql : " + sql);

        MemoDatabase database = MemoDatabase.getInstance(context);
        database.execSQL(sql);

    }

    private void modifyMemo() {
        if (item != null) {
            String themeGet = theme.getText().toString();
            String contentGet = contents.getText().toString();

            String sql = "update " + MemoDatabase.TABLE_NOTE +
                    " set " +
                    "   THEME = '" + themeGet + "'" +
                    "   ,CONTENTS = '" + contentGet + "'" +
                    " where " +
                    " _id = " + item.id;

            Log.d(TAG, "sql : " + sql);
            MemoDatabase database = MemoDatabase.getInstance(context);
            database.execSQL(sql);


        }
    }

    private void deleteNote() {
        AppConstants.println("deleteDiary called");
        if (item != null) {
            String sql = "delete from " + MemoDatabase.TABLE_NOTE +
                    " where " +
                    " _id = " + item.id;

            Log.d(TAG, "sql : " + sql);
            MemoDatabase database = MemoDatabase.getInstance(context);
            database.execSQL(sql);
        }
    }

    public void setItem(Memo item) {
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