package com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelperHistory extends SQLiteOpenHelper {

    private static final String dbname="history";
    private static  final int version=1;

    public MyHelperHistory(Context context) {
        super(context, dbname, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlhistory="CREATE TABLE  TRANSFER_TABLE  (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME1 TEXT,NAME2 TEXT,AMOUNT_TRANSFERED REAL)";
        sqLiteDatabase.execSQL(sqlhistory);

        SelectToSend s=new SelectToSend();
        double d =s.getSendingbal();
        String s1=s.getName2();
        String s2=s.getName1();
        insertHistory(s1,s2,d,sqLiteDatabase);
        insertHistory("prashant","tanu",200,sqLiteDatabase);
    }
    protected void insertHistory(String name1, String name2, double amount,SQLiteDatabase db){

        ContentValues values=new ContentValues();
        values.put("NAME1",name1);
        values.put("NAME2",name2);
        values.put("AMOUNT_TRANSFERED",amount);
        db.insert("TRANSFER_TABLE",null,values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
