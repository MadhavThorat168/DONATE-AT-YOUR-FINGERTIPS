package com.example.donation_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;import com.example.donation_app.History;

import java.util.ArrayList;

public class DhistoryAdapter extends RecyclerView.Adapter<DhistoryAdapter.ViewHolder> {


    ArrayList<History> mList;
    Context context;

    public DhistoryAdapter(ArrayList<History> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History vacancy1 = mList.get(position);
        holder.txtbname.setText("Recevier Name : "+vacancy1.getRname());
        holder.txtaddress.setText("Number : "+vacancy1.getRnumber());
        holder.txttype.setText("Address : "+vacancy1.getRaddress());
        holder.txtitem4.setText("qty "+vacancy1.getItem());
        holder.txtitem5.setText("item "+vacancy1.getQty());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {


        TextView txtbname,txtaddress,txttype,txtitem4,txtitem5;

        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            txtbname = itemView.findViewById(R.id.txtbname);
            txtaddress = itemView.findViewById(R.id.txtprofile);
            txttype = itemView.findViewById(R.id.txtaddress);
            txtitem5 = itemView.findViewById(R.id.txtitem5);
            txtitem4 = itemView.findViewById(R.id.txtitem4);
            relativeLayout = itemView.findViewById(R.id.relative);

        }



    }

}
