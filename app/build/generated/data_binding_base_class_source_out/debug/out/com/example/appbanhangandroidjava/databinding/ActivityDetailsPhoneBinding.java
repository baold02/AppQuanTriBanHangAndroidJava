// Generated by view binder compiler. Do not edit!
package com.example.appbanhangandroidjava.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appbanhangandroidjava.R;
import com.nex3z.notificationbadge.NotificationBadge;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailsPhoneBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAddToCart;

  @NonNull
  public final FrameLayout frameCart;

  @NonNull
  public final ImageView imgChiTiet;

  @NonNull
  public final LinearLayout layout1;

  @NonNull
  public final NotificationBadge menuSl;

  @NonNull
  public final Spinner spinner;

  @NonNull
  public final Toolbar toolbarChiTiet;

  @NonNull
  public final TextView tvNameDetails;

  @NonNull
  public final TextView tvpriceDetails;

  @NonNull
  public final TextView txtMoTaDetails;

  private ActivityDetailsPhoneBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnAddToCart, @NonNull FrameLayout frameCart, @NonNull ImageView imgChiTiet,
      @NonNull LinearLayout layout1, @NonNull NotificationBadge menuSl, @NonNull Spinner spinner,
      @NonNull Toolbar toolbarChiTiet, @NonNull TextView tvNameDetails,
      @NonNull TextView tvpriceDetails, @NonNull TextView txtMoTaDetails) {
    this.rootView = rootView;
    this.btnAddToCart = btnAddToCart;
    this.frameCart = frameCart;
    this.imgChiTiet = imgChiTiet;
    this.layout1 = layout1;
    this.menuSl = menuSl;
    this.spinner = spinner;
    this.toolbarChiTiet = toolbarChiTiet;
    this.tvNameDetails = tvNameDetails;
    this.tvpriceDetails = tvpriceDetails;
    this.txtMoTaDetails = txtMoTaDetails;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailsPhoneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailsPhoneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_details_phone, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailsPhoneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAddToCart;
      Button btnAddToCart = ViewBindings.findChildViewById(rootView, id);
      if (btnAddToCart == null) {
        break missingId;
      }

      id = R.id.frameCart;
      FrameLayout frameCart = ViewBindings.findChildViewById(rootView, id);
      if (frameCart == null) {
        break missingId;
      }

      id = R.id.imgChiTiet;
      ImageView imgChiTiet = ViewBindings.findChildViewById(rootView, id);
      if (imgChiTiet == null) {
        break missingId;
      }

      id = R.id.layout1;
      LinearLayout layout1 = ViewBindings.findChildViewById(rootView, id);
      if (layout1 == null) {
        break missingId;
      }

      id = R.id.menu_sl;
      NotificationBadge menuSl = ViewBindings.findChildViewById(rootView, id);
      if (menuSl == null) {
        break missingId;
      }

      id = R.id.spinner;
      Spinner spinner = ViewBindings.findChildViewById(rootView, id);
      if (spinner == null) {
        break missingId;
      }

      id = R.id.toolbarChiTiet;
      Toolbar toolbarChiTiet = ViewBindings.findChildViewById(rootView, id);
      if (toolbarChiTiet == null) {
        break missingId;
      }

      id = R.id.tvNameDetails;
      TextView tvNameDetails = ViewBindings.findChildViewById(rootView, id);
      if (tvNameDetails == null) {
        break missingId;
      }

      id = R.id.tvpriceDetails;
      TextView tvpriceDetails = ViewBindings.findChildViewById(rootView, id);
      if (tvpriceDetails == null) {
        break missingId;
      }

      id = R.id.txtMoTaDetails;
      TextView txtMoTaDetails = ViewBindings.findChildViewById(rootView, id);
      if (txtMoTaDetails == null) {
        break missingId;
      }

      return new ActivityDetailsPhoneBinding((ConstraintLayout) rootView, btnAddToCart, frameCart,
          imgChiTiet, layout1, menuSl, spinner, toolbarChiTiet, tvNameDetails, tvpriceDetails,
          txtMoTaDetails);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
