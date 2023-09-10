package com.example.appbanhangandroidjava.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.interfaces.ImageClickListener;
import com.example.appbanhangandroidjava.models.EventBus.SumEvent;
import com.example.appbanhangandroidjava.models.GioHang;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    List<GioHang> gioHangList;

    public CartAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        Picasso.with(context).load(gioHangList.get(position).getHinhsp()).into(holder.imageView);
        holder.item_tvNameProduct.setText(gioHang.getTensp());
        holder.item_soLuong.setText(gioHang.getSoluong() + "");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_price1.setText( decimalFormat.format(gioHang.getGiasp()));
        long gia = gioHang.getSoluong() * gioHang.getGiasp();
        holder.item_gia2.setText(decimalFormat.format(gia));
        Log.d("name:",gioHang.getTensp());
        Log.d("sl:",String.valueOf(gioHang.getSoluong()));

        holder.setListener(new ImageClickListener() {
            @Override
            public void onImageClick(View view, int pos, int value) {
                if(value == 1){
                      if(gioHangList.get(pos).getSoluong() >1){
                          int newValue = gioHangList.get(pos).getSoluong() - 1;
                          gioHangList.get(pos).setSoluong(newValue);

                          holder.item_soLuong.setText(gioHangList.get(pos).getSoluong() + "");
                          long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                          holder.item_gia2.setText(decimalFormat.format(gia));
                          EventBus.getDefault().postSticky(new SumEvent());
                      }else if(gioHangList.get(pos).getSoluong() == 1){
                          AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                          builder.setTitle("Thông báo");
                          builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
                          builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  Utils.mangGioHang.remove(pos);
                                  notifyDataSetChanged();
                              }
                          });
                          builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  dialog.dismiss();
                              }
                          });
                          builder.show();

                      }
                }else  if(value == 2){
                    if(gioHangList.get(pos).getSoluong() < 11){
                        int newValue2 = gioHangList.get(pos).getSoluong() +1;
                        gioHangList.get(pos).setSoluong(newValue2);
                    }

                    holder.item_soLuong.setText(gioHangList.get(pos).getSoluong() + "");
                    long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                    holder.item_gia2.setText(decimalFormat.format(gia));
                    EventBus.getDefault().postSticky(new SumEvent());

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView, imageRemove, imageAdd;
        TextView item_tvNameProduct, item_price1, item_soLuong,item_gia2;
        ImageClickListener listener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tvNameProduct = itemView.findViewById(R.id.item_name_cart);
            imageView = itemView.findViewById(R.id.img_gioHang);
            item_price1 = itemView.findViewById(R.id.price_cart1);
            item_soLuong = itemView.findViewById(R.id.cart_sl);
            item_gia2 = itemView.findViewById(R.id.price);
            imageAdd = itemView.findViewById(R.id.img_add);
            imageRemove = itemView.findViewById(R.id.img_remove);

            imageAdd.setOnClickListener(this);
            imageRemove.setOnClickListener(this);
        }
        public  void setListener(ImageClickListener listener){
            this.listener = listener;
        }

        @Override
        public void onClick(View view){
         if(view == imageRemove){
             listener.onImageClick(view,getAdapterPosition(),1);
         }else if (view == imageAdd){
             listener.onImageClick(view,getAdapterPosition(),2);
         }
        }
    }
}
