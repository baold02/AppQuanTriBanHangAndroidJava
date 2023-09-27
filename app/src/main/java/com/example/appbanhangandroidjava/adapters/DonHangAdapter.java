package com.example.appbanhangandroidjava.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.models.Item;
import com.example.appbanhangandroidjava.models.Order;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
   List<Item> item;

    public DonHangAdapter(List<Item> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       Item item1 = item.get(position);
       holder.tvName.setText(item1.getTensp() + "");
        Log.e("abc", item1.getTensp() +"");
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameHistory);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }
    }
}
