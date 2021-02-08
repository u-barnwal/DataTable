package com.isolpro.library.datatable;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class TopHeaderTextView extends AppCompatTextView {

  public TopHeaderTextView(Context context) {
    this(context, null);
  }

  public TopHeaderTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TopHeaderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

}
