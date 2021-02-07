package com.isolpro.library.datatable;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class ColumnHeaderTextView extends AppCompatTextView {

  public ColumnHeaderTextView(Context context) {
    this(context, null);
  }

  public ColumnHeaderTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ColumnHeaderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

}
