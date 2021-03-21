package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {

    ArrayList<String> arrayListname1=new ArrayList<>();
    ArrayList<String> arrayListname2=new ArrayList<>();
    ArrayList<Double> arrayListTransferedBalance=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);


        RecyclerView myview1=findViewById(R.id.Historyrecycler);
        myview1.setLayoutManager(new LinearLayoutManager(this));

        MyHelperHistory myHelper=new MyHelperHistory(this);

        SQLiteDatabase database=myHelper.getWritableDatabase();

        Cursor cursor=database.rawQuery("SELECT NAME1,NAME2,AMOUNT_TRANSFERED FROM TRANSFER_TABLE",new String[]{} );
        if(cursor!=null){
            cursor.moveToFirst();
        }
        /*else{
            TextView textView=new TextView(this);
            textView.setText("No history");
            textView.setTextSize(20);
        }*/


        do{

            String name1 = cursor.getString(0);
            String name2 = cursor.getString(1);
            double transferedbalance = cursor.getDouble(2);

            arrayListname1.add (name1) ;
            arrayListname2.add(name2);
            arrayListTransferedBalance.add(transferedbalance);
        }while(cursor.moveToNext());

        myview1.setAdapter(new MyHistoryAdaptor(arrayListname1,arrayListname2,arrayListTransferedBalance));
    }

}