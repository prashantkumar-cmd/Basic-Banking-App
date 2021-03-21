package com.example.basicbankingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
         private  ListItemClickListener mOnClickListener;
      private ArrayList<String> data=new ArrayList<>();
      private ArrayList<String> emailData=new ArrayList<>();

      private ArrayList<Double> balanceData=new ArrayList<>();



        public MyAdapter(ListItemClickListener mOnClickListener, ArrayList<String> data1, ArrayList<String> emailData, ArrayList<Double> balanceData){
            this.mOnClickListener = mOnClickListener;
            this.data=data1;
            this.emailData=emailData;
            this.balanceData=balanceData;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View view=inflater.inflate(R.layout.list_item_layout,parent,false);
            return new MyViewHolder(view,mOnClickListener) ;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String title=data.get(position);
            String email=emailData.get(position);
            Double balance=balanceData.get(position);
            holder.textView.setText(title);
            holder.textView1.setText(email);
            holder.textView2.setText(""+balance);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView textView,textView1,textView2;
            ListItemClickListener listItemClickListener;
            public MyViewHolder(View item,ListItemClickListener listItemClickListener){
                super(item);
                this.listItemClickListener=listItemClickListener;

                item.setOnClickListener(this);

                textView=item.findViewById(R.id.text1);
                textView1=item.findViewById(R.id.email);
                textView2=item.findViewById(R.id.balance);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                listItemClickListener.onListItemClick(position);
            }
        }
    interface ListItemClickListener{
        void onListItemClick(int position);

    }

}
