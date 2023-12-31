// Generated by view binder compiler. Do not edit!
package com.example.appbanhangandroidjava.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class ActivityPayImmediatelyBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnDatHang;

  @NonNull
  public final EditText edtLocation;

  @NonNull
  public final LinearLayout layout1;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvPhone;

  @NonNull
  public final TextView tvSumprice;

  private ActivityPayImmediatelyBinding(@NonNull LinearLayout rootView, @NonNull Button btnDatHang,
      @NonNull EditText edtLocation, @NonNull LinearLayout layout1, @NonNull TextView tvEmail,
      @NonNull TextView tvPhone, @NonNull TextView tvSumprice) {
    this.rootView = rootView;
    this.btnDatHang = btnDatHang;
    this.edtLocation = edtLocation;
    this.layout1 = layout1;
    this.tvEmail = tvEmail;
    this.tvPhone = tvPhone;
    this.tvSumprice = tvSumprice;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPayImmediatelyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPayImmediatelyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_pay_immediately, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPayImmediatelyBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDatHang;
      Button btnDatHang = ViewBindings.findChildViewById(rootView, id);
      if (btnDatHang == null) {
        break missingId;
      }

      id = R.id.edtLocation;
      EditText edtLocation = ViewBindings.findChildViewById(rootView, id);
      if (edtLocation == null) {
        break missingId;
      }

      LinearLayout layout1 = (LinearLayout) rootView;

      id = R.id.tvEmail;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tvPhone;
      TextView tvPhone = ViewBindings.findChildViewById(rootView, id);
      if (tvPhone == null) {
        break missingId;
      }

      id = R.id.tvSumprice;
      TextView tvSumprice = ViewBindings.findChildViewById(rootView, id);
      if (tvSumprice == null) {
        break missingId;
      }

      return new ActivityPayImmediatelyBinding((LinearLayout) rootView, btnDatHang, edtLocation,
          layout1, tvEmail, tvPhone, tvSumprice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
