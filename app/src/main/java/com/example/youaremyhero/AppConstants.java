package com.example.youaremyhero;

import android.util.Log;

public class AppConstants {
    public static final int MODE_INSERT = 1;
    public static final int MODE_MODIFY = 2;
    private static final String TAG = "DiaryDatabase";
    public static final String DATABASE_NAME = "diary.db";

    public static void println(String msg){
        Log.d(TAG,msg);
    }
}
