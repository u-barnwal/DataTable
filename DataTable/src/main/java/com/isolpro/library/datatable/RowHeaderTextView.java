package com.isolpro.library.datatable;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class RowHeaderTextView extends AppCompatTextView {

  public RowHeaderTextView(Context context) {
    this(context, null);
  }

  public RowHeaderTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RowHeaderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

}
