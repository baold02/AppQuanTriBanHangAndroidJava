package com.example.appbanhangandroidjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Screens.DetailsPhoneActivity;
import com.example.appbanhangandroidjava.interfaces.ItemClickListener;
import com.example.appbanhangandroidjava.models.SanPhamNew;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SanPhamNew> list;
    private static final int  VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public PhoneAdapter(Context context, List<SanPhamNew> list) {
        this.context = context;
        this.list = list;
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone,parent,false);
//        return new ViewHolder(view);
//    }


//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//       SanPhamNew sanPhamNew = list.get(position);
//       holder.tvNamePhone.setText(sanPhamNew.getTensp());
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_DATA){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone,parent,false);
           return new ViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHodel(view);
        }
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          if(holder instanceof  ViewHolder){
              ViewHolder viewHolder = (ViewHolder) holder;
              SanPhamNew sanPhamNew = list.get(position);
              viewHolder.tvNamePhone.setText(sanPhamNew.getTensp());
             viewHolder.setItemClickListener(new ItemClickListener() {
                 @Override
                 public void onClick(View view, int pos, boolean isLongClick) {
                     if(!isLongClick){
                         Intent intent = new Intent(context, DetailsPhoneActivity.class);
                         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         context.startActivity(intent);
                     }
                 }
             });
          }else {
              LoadingViewHodel loadingViewHodel = (LoadingViewHodel) holder;
              loadingViewHodel.progressBar.setIndeterminate(true);
          }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class LoadingViewHodel extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHodel(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progessbar);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNamePhone, tvPricePhone, tvDesPhone;
        ImageView imgPhone;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamePhone = itemView.findViewById(R.id.tv_namePhone);
            tvPricePhone = itemView.findViewById(R.id.tv_pricePhone);
            tvDesPhone = itemView.findViewById(R.id.tv_desPhone);
            imgPhone = itemView.findViewById(R.id.img_phone);
            itemView.setOnClickListener(this);
        }
       public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
       }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
