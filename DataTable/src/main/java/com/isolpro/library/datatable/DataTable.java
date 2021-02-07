package com.isolpro.library.datatable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class DataTable extends RelativeLayout {

  private final Context context;
  private final AttributeSet attributeSet;

  private View mainView;

  public DataTable(Context context) {
    this(context, null);
  }

  public DataTable(Context context, AttributeSet attrs) {
    super(context, attrs);

    this.context = context;
    this.attributeSet = attrs;

    initialize();
  }

  private void initialize() {
    mainView = LayoutInflater.from(context).inflate(R.layout.data_table, null);
    addView(mainView);
  }

}
