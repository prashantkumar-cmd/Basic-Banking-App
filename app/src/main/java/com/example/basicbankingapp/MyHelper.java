package com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyHelper extends SQLiteOpenHelper
{
private static final String dbname="Customers";
private static  final int version=1;

    public MyHelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE  CUSTOMERS (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,ACCOUNT_BALANCE REAL)";

        sqLiteDatabase.execSQL(sql);

        insertData("Prashant","prashantkumarjnv6@gmail.com",4500,sqLiteDatabase);
        insertData("Ashish","ashish321@gmail.com",5500,sqLiteDatabase);
        insertData("Ishan","ishan9906@gmail.com",5000,sqLiteDatabase);
        insertData("ronit","ronit550@gmail.com",4100,sqLiteDatabase);
        insertData("Ahzam","mdAhzam37@gmail.com",7300,sqLiteDatabase);
        insertData("Mantu","mantuf56@gmail.com",4800,sqLiteDatabase);
        insertData("Shubham","Shubham0087@gmail.com",7000,sqLiteDatabase);
        insertData("Ashwini","ashwini233@gmail.com",6500,sqLiteDatabase);
        insertData("shana","shana007@gmail.com",10000,sqLiteDatabase);
        insertData("Tanu","tanu88822@gmail.com",4700,sqLiteDatabase);

    }


    private void insertData(String name,String email,double balance,SQLiteDatabase database){
        ContentValues values=new ContentValues();
        values.put("NAME",name);
        values.put("EMAIL",email);
        values.put("ACCOUNT_BALANCE",balance);
        database.insert("CUSTOMERS",null,values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
    public boolean ontransferAmt(String recievername, String updatedAmt){
      SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ACCOUNT_BALANCE",updatedAmt);
        database.update("CUSTOMERS",values,"NAME=?",new String[]{recievername});
      return  true;
    }

}
