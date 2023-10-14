// Generated by view binder compiler. Do not edit!
package com.example.appbanhangandroidjava.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appbanhangandroidjava.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCartBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnMuaHang;

  @NonNull
  public final LinearLayout layout1;

  @NonNull
  public final RecyclerView rcvCart;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView tvGioHangTrong;

  @NonNull
  public final TextView tvTongTien;

  private ActivityCartBinding(@NonNull LinearLayout rootView, @NonNull Button btnMuaHang,
      @NonNull LinearLayout layout1, @NonNull RecyclerView rcvCart, @NonNull Toolbar toolbar,
      @NonNull TextView tvGioHangTrong, @NonNull TextView tvTongTien) {
    this.rootView = rootView;
    this.btnMuaHang = btnMuaHang;
    this.layout1 = layout1;
    this.rcvCart = rcvCart;
    this.toolbar = toolbar;
    this.tvGioHangTrong = tvGioHangTrong;
    this.tvTongTien = tvTongTien;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_cart, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnMuaHang;
      Button btnMuaHang = ViewBindings.findChildViewById(rootView, id);
      if (btnMuaHang == null) {
        break missingId;
      }

      id = R.id.layout1;
      LinearLayout layout1 = ViewBindings.findChildViewById(rootView, id);
      if (layout1 == null) {
        break missingId;
      }

      id = R.id.rcvCart;
      RecyclerView rcvCart = ViewBindings.findChildViewById(rootView, id);
      if (rcvCart == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tvGioHangTrong;
      TextView tvGioHangTrong = ViewBindings.findChildViewById(rootView, id);
      if (tvGioHangTrong == null) {
        break missingId;
      }

      id = R.id.tvTongTien;
      TextView tvTongTien = ViewBindings.findChildViewById(rootView, id);
      if (tvTongTien == null) {
        break missingId;
      }

      return new ActivityCartBinding((LinearLayout) rootView, btnMuaHang, layout1, rcvCart, toolbar,
          tvGioHangTrong, tvTongTien);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}