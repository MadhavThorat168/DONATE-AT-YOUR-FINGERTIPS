package com.example.donation_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<book> mList;
    private Context context;

    public BookAdapter(ArrayList<book> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        book vacancy1 = mList.get(position);
        holder.txtbname.setText("Donor Name " + vacancy1.getName());
        holder.txtaddress.setText("Number " + vacancy1.getNumber());
        holder.txttype.setText("Address " + vacancy1.getAddress());
        Glide.with(holder.img1.getContext()).load(vacancy1.getImageurl()).into(holder.img1);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Bookdeatils.class);


                intent.putExtra("proname", vacancy1.getName());
                intent.putExtra("material", vacancy1.getAddress());
                intent.putExtra("manifacture", vacancy1.getNumber());
                intent.putExtra("authorname", vacancy1.getBook());
                intent.putExtra("qty", vacancy1.getQty());
                intent.putExtra("url", vacancy1.getImageurl());

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView txtbname, txtaddress, txttype;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            relativeLayout = itemView.findViewById(R.id.relative);
        }
    }
}
