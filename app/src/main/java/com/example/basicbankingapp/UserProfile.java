package com.example.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        TextView t1=findViewById(R.id.name1);
        TextView t2=findViewById(R.id.name3);
        TextView t3=findViewById(R.id.name4);

        String passedName = getIntent().getExtras().getString("name");
        String passedEmail = getIntent().getExtras().getString("email");
        String passedBalance = getIntent().getExtras().getString("balance");
        int passedPosition=getIntent().getExtras().getInt("senderPosition");
        t1.setText(passedName);
        t2.setText(passedEmail);
        t3.setText(passedBalance);
        btn=findViewById(R.id.moneytransfer);
        btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
             builder.setTitle("Enter Amount to Transfer");
             final  EditText enterAmt= new EditText(UserProfile.this);
             enterAmt.setInputType(InputType.TYPE_CLASS_NUMBER);
             builder.setView(enterAmt);

             builder.setPositiveButton("Send to",new DialogInterface.OnClickListener(){
                 public void onClick(DialogInterface arg0, int arg1) {
                     if(enterAmt.getText().toString().isEmpty())
                         error();
                     else if(Double.parseDouble(enterAmt.getText().toString())>Double.parseDouble(t3.getText().toString())){
                        error2();
                     }
                     else{

                         Intent intent = new Intent(UserProfile.this, SelectToSend.class);
                         intent.putExtra("amountextra", enterAmt.getText().toString());
                         intent.putExtra("senderPosition1", passedPosition);
                         startActivity(intent);
                         finish();
                     }

                 }
             }).show();
           }

        });


    }

    public void error(){
        Toast.makeText(this,"AMOUNT CAN'T BE EMPTY",Toast.LENGTH_LONG).show();
    }
    public void error2(){
        Toast.makeText(this,"AMOUNT CAN'T BE GREATER THAN CURRENT BALANCE ",Toast.LENGTH_LONG).show();
    }

}