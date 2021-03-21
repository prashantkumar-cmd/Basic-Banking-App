package com.example.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectToSend extends AppCompatActivity implements MyAdapter.ListItemClickListener {
   public ArrayList<String> arrayList = new ArrayList<>();
   public ArrayList<String> arrayListEmail = new ArrayList<>();
   public ArrayList<Double> arrayListbalance = new ArrayList<>();

    double sendingbal;
    static double msendingbal;
    int senderPosition;
    static String mname1,mname2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_select_to_send);
           String s=getIntent().getStringExtra("amountextra");
           senderPosition=getIntent().getExtras().getInt("senderPosition1");
           sendingbal=Double.parseDouble(s);

            RecyclerView myview=findViewById(R.id.recycler2);
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
                arrayListbalance.add(balance);
            }while(cursor.moveToNext());


            myview.setAdapter(new MyAdapter(this, arrayList,arrayListEmail,arrayListbalance));

    }

        @Override
        public void onListItemClick(int position) {

        String name1=arrayList.get(position);
        double increasedbal=sendingbal+arrayListbalance.get(position);
      String  amount1=Double.toString(increasedbal);
         MyHelper myHelper=new MyHelper(this);
         myHelper.ontransferAmt(name1,amount1);
         updatesendersAccount(senderPosition);
         setName1(name1);
         setAmt(sendingbal);
         Intent i=new Intent(this,CustomerList.class);
         startActivity(i);
            confirm();
        }


        public void updatesendersAccount(int positionX){
            String name2=arrayList.get(positionX);
            double remainingbal=arrayListbalance.get(positionX)-sendingbal;
           String amount2=Double.toString(remainingbal);
            MyHelper myHelper=new MyHelper(this);
            myHelper.ontransferAmt(name2,amount2);
            setName2(name2);

        }


        public void setName1(String s){
          mname1=s;

        }
    public void setName2(String s){
         mname2=s;

    }
    public void setAmt(double s){
        msendingbal=s;
    }
    public String getName1(){
        return mname1;
    }
    public String getName2(){
        return mname2;
    }
    public double getSendingbal(){
        return msendingbal;
    }

    void confirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Amount successfully sent ");
        builder.setIcon(R.drawable.check_mark);

    }

}
