package com.example.basicbankingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyHistoryAdaptor extends RecyclerView.Adapter<MyHistoryAdaptor.MyViewHolder2> {

    private ArrayList<String> arrayListname1=new ArrayList<>();
    private ArrayList<String> arrayListname2=new ArrayList<>();
    private ArrayList<Double> arrayListTransferedBalance=new ArrayList<>();

    public MyHistoryAdaptor(ArrayList<String> name1, ArrayList<String> name2, ArrayList<Double> arrayListTransferedBalance){
        this.arrayListname1=name1;
        this.arrayListname2=name2;
        this.arrayListTransferedBalance=arrayListTransferedBalance;
    }


    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.history_layout,parent,false);
        return new MyHistoryAdaptor.MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        String customer1=arrayListname1.get(position);
        String customer2=arrayListname2.get(position);
        Double transaction=arrayListTransferedBalance.get(position);
        holder.textView.setText(customer1);
        holder.textView1.setText(customer2);
        holder.textView2.setText(""+transaction);
    }

    @Override
    public int getItemCount() {
        return arrayListname1.size();
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView, textView1, textView2;

        public MyViewHolder2(View item) {
            super(item);
            textView = item.findViewById(R.id.customer_1);
            textView1 = item.findViewById(R.id.customer_2);
            textView2 = item.findViewById(R.id.rupees);
        }
    }
}
