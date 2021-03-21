package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class HomeActivity extends AppCompatActivity {
     MeowBottomNavigation bottomnav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomnav=findViewById(R.id.bottom_nav);

        bottomnav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notification));
        bottomnav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomnav.add(new MeowBottomNavigation.Model(3,R.drawable.ic_info));

        bottomnav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment  fragment=null;

                switch (item.getId()){
                    case 1:
                        fragment=new NotificationFragment();
                        break;
                    case 2:
                        fragment=new Homefragment();
                        break;
                    case 3:
                        fragment=new AboutFragment();
                }
                loadFragment(fragment);
            }
        });

        bottomnav.setCount(1,"1");
        bottomnav.show(2,true);
        bottomnav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(HomeActivity.this, ""+menuName(item.getId()), Toast.LENGTH_SHORT).show();
            }
        });
        bottomnav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(HomeActivity.this,  ""+menuName(item.getId()), Toast.LENGTH_SHORT).show();
            }
        });

    }
    String menuName(int n){
       if(n==1)
           return "Notification";
       else if(n==2)
           return "Home" ;
       else
           return "About us";
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();
    }

    public void customerLists(View view) {

            Intent i=new Intent(this,CustomerList.class);
            startActivity(i);

    }


    public void History(View view) {
        Intent i=new Intent(this,TransactionHistory.class);
        startActivity(i);
    }
}