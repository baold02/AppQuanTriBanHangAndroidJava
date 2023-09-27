package com.example.appbanhangandroidjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.models.Order;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHOlder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<Order> orders;

    public HistoryAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        return new MyViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHOlder holder, int position) {
        Order order = orders.get(position);
        holder.tvId.setText("DOn hang" + order.getId());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rcvOrder.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(order.getItem().size());

        DonHangAdapter donHangAdapter = new DonHangAdapter(order.getItem());
        holder.rcvOrder.setLayoutManager(layoutManager);
        holder.rcvOrder.setAdapter(donHangAdapter);
        holder.rcvOrder.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder{
        TextView tvId;
        RecyclerView rcvOrder;
        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            tvId =itemView.findViewById(R.id.tvId);
            rcvOrder = itemView.findViewById(R.id.rcvOrder);
        }
    }
}
