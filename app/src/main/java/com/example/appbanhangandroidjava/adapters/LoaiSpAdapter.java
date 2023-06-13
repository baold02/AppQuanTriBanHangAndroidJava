package com.example.appbanhangandroidjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.models.LoaiSp;

import java.util.List;

public class LoaiSpAdapter extends BaseAdapter {
    List<LoaiSp> loaiSps;
    Context context;


    public LoaiSpAdapter(List<LoaiSp> loaiSps, Context context) {
        this.loaiSps = loaiSps;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaiSps.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView tvTenLoai;
        ImageView imgHinhLoai;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view  == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_loaisp,null);
            viewHolder.tvTenLoai = view.findViewById(R.id.tvLoai);
            viewHolder.imgHinhLoai = view.findViewById(R.id.imgLoai);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvTenLoai.setText(loaiSps.get(i).getTensanpham());
        return view;
    }
}
