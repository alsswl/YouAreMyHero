package com.example.youaremyhero;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DiaryDatabase {

    private static final String TAG = "DiaryDatabase";

    private static DiaryDatabase database;
    public static String TABLE_NOTE = "Diary";
    public static int DATABASE_VERSION = 1;

    public DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private DiaryDatabase(Context context){
        this.context = context;
    }

    public static DiaryDatabase getInstance(Context context){
        if(database == null){
            database = new DiaryDatabase(context);
        }
        return database;
    }

    public boolean open(){
        println("opening database["+AppConstants.DATABASE_NAME + "]");

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    public void close(){
        println("closing database [" + AppConstants.DATABASE_NAME +"].");
        db.close();

        database = null;
    }

    public Cursor rawQuery(String SQL){
        println("\nexecuteQuery called.\n");

        Cursor cursor = null;
        try{
            cursor = db.rawQuery(SQL,null);
            println("cursor count : "+cursor.getCount());
        }catch (Exception ex){
            Log.e(TAG,"Exception in executeQuery",ex);
        }

        return cursor;
    }

    public boolean execSQL(String SQL){
        println("\nexecute called.\n");

        try{
            Log.d(TAG,"SQL : " +SQL);
            db.execSQL(SQL);
        }catch(Exception ex){
            Log.e(TAG,"Exception in executeQuery",ex);
            return false;
        }
        return true;
    }

    private class DatabaseHelper extends SQLiteOpenHelper{


        public DatabaseHelper(Context context){
            super(context, AppConstants.DATABASE_NAME, null,DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            println("creating database [" +AppConstants.DATABASE_NAME+"].");

            println("creating table {" +TABLE_NOTE+"].");

            String DROP_SQL = "drop table if exists " + TABLE_NOTE;

            try{
                db.execSQL(DROP_SQL);
            } catch (Exception ex){
                Log.e(TAG,"Exception in DROP_SQL", ex);
            }

            String CREATE_SQL = "create table "+TABLE_NOTE+"("
                    +"_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    +" PLACE TEXT DEFAULT '', "
                    +" PARENTCONTENTS TEXT DEFAULT '', "
                    +" MOOD TEXT, "
                    +" BABYCONTENTS TEXT DEFAULT '', "
                    +" NEWONE TEXT DEFAULT '', "
                    +" THEME TEXT DEFAULT '', "
                    +" CREATEDATESTR TEXT DEFAULT '' "
                    + ")";

            try{
                db.execSQL(CREATE_SQL);
            }catch(Exception ex){
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            String CREATE_INDEX_SQL = "create index " + TABLE_NOTE + "_IDX ON "+TABLE_NOTE+"("
                    +"CREATE_DATE"
                    +")";

            try{
                db.execSQL(CREATE_INDEX_SQL);
            }catch(Exception ex){
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", ex);
            }

        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            println("opened database [" +AppConstants.DATABASE_NAME+"].");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            println("opened database [" +AppConstants.DATABASE_NAME+"].");
        }



    }
    private void println(String msg){
        Log.d(TAG,msg);
    }
}
