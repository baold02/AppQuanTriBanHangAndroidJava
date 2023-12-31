// Generated by view binder compiler. Do not edit!
package com.example.appbanhangandroidjava.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appbanhangandroidjava.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemLoaispBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView imgLoai;

  @NonNull
  public final LinearLayout layout;

  @NonNull
  public final TextView tvLoai;

  private ItemLoaispBinding(@NonNull LinearLayout rootView, @NonNull ImageView imgLoai,
      @NonNull LinearLayout layout, @NonNull TextView tvLoai) {
    this.rootView = rootView;
    this.imgLoai = imgLoai;
    this.layout = layout;
    this.tvLoai = tvLoai;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemLoaispBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemLoaispBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_loaisp, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemLoaispBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgLoai;
      ImageView imgLoai = ViewBindings.findChildViewById(rootView, id);
      if (imgLoai == null) {
        break missingId;
      }

      LinearLayout layout = (LinearLayout) rootView;

      id = R.id.tvLoai;
      TextView tvLoai = ViewBindings.findChildViewById(rootView, id);
      if (tvLoai == null) {
        break missingId;
      }

      return new ItemLoaispBinding((LinearLayout) rootView, imgLoai, layout, tvLoai);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
