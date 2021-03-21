package com.example.basicbankingapp;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity implements MyAdapter.ListItemClickListener {

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayListEmail=new ArrayList<>();
    ArrayList<Double> arrayListBalance=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);


        RecyclerView myview=findViewById(R.id.recycler);
        myview.setLayoutManager(new LinearLayoutManager(this));

        MyHelper myHelper=new MyHelper(this);

        SQLiteDatabase database=myHelper.getWritableDatabase();

        Cursor cursor=database.rawQuery("SELECT NAME ,EMAIL,ACCOUNT_BALANCE FROM CUSTOMERS",new String[]{});
        if(cursor!=null){
            cursor.moveToFirst();
        }


        do{

            String name = cursor.getString(0);
            String email = cursor.getString(1);
            double balance = cursor.getDouble(2);

            arrayList.add (name) ;
            arrayListEmail.add(email);
            arrayListBalance.add(balance);
         }while(cursor.moveToNext());


        myview.setAdapter(new MyAdapter(this, arrayList,arrayListEmail,arrayListBalance));


    }

    @Override
    public void onListItemClick(int position) {

        Intent i=new Intent(this,UserProfile.class);
        i.putExtra("name",arrayList.get(position));
        i.putExtra("email",arrayListEmail.get(position));
        i.putExtra("balance",""+arrayListBalance.get(position));
        i.putExtra("senderPosition",position);
        startActivity(i);

    }


}