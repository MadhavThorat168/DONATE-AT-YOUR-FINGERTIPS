package com.example.donation_app;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RhistoryAdapter extends RecyclerView.Adapter<RhistoryAdapter.ViewHolder> {

    private ArrayList<History> mList;
    private Context mContext;

    public RhistoryAdapter(ArrayList<History> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position >= 0 && position < mList.size()) {
            History historyItem = mList.get(position);

            // Handle potential null values before setting text
            holder.txtDonorName.setText("Donor Name: " + (historyItem.getDname() != null ? historyItem.getDname() : ""));
            holder.txtItem.setText("Item: " + (historyItem.getItem() != null ? historyItem.getItem() : ""));
            holder.txtQty.setText("Qty: " + (historyItem.getQty() != null ? historyItem.getQty().toString() : ""));
        } else {
            // Optionally handle case where position is out of bounds
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDonorName, txtItem, txtQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDonorName = itemView.findViewById(R.id.txtbname);
            txtItem = itemView.findViewById(R.id.txtprofile);
            txtQty = itemView.findViewById(R.id.txtaddress);
        }
    }
}
