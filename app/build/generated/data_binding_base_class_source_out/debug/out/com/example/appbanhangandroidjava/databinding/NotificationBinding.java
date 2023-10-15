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

public final class NotificationBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView imgNoti;

  @NonNull
  public final TextView tvContentNoti;

  @NonNull
  public final TextView tvtitleNoti;

  private NotificationBinding(@NonNull LinearLayout rootView, @NonNull ImageView imgNoti,
      @NonNull TextView tvContentNoti, @NonNull TextView tvtitleNoti) {
    this.rootView = rootView;
    this.imgNoti = imgNoti;
    this.tvContentNoti = tvContentNoti;
    this.tvtitleNoti = tvtitleNoti;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NotificationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NotificationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.notification, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NotificationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgNoti;
      ImageView imgNoti = ViewBindings.findChildViewById(rootView, id);
      if (imgNoti == null) {
        break missingId;
      }

      id = R.id.tvContentNoti;
      TextView tvContentNoti = ViewBindings.findChildViewById(rootView, id);
      if (tvContentNoti == null) {
        break missingId;
      }

      id = R.id.tvtitleNoti;
      TextView tvtitleNoti = ViewBindings.findChildViewById(rootView, id);
      if (tvtitleNoti == null) {
        break missingId;
      }

      return new NotificationBinding((LinearLayout) rootView, imgNoti, tvContentNoti, tvtitleNoti);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
