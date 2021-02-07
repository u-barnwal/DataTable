package com.isolpro.library.datatable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

public class DataTable extends TableLayout {

  private final Context context;
  private final AttributeSet attributeSet;

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
    setStretchAllColumns(true);
//    setShrinkAllColumns(true);
  }

}
