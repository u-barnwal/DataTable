package com.isolpro.library.datatable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

public class DataTable extends RelativeLayout {

  private final Context context;
  private final AttributeSet attributeSet;

  private final View mainView;

  private TableLayout tlRowHeader, tlColumnHeader, tlBody;

  private RelativeLayout layoutCorner, layoutColumnHeader, layoutRowHeader, layoutBody;

  private HorizontalScrollView hsvBody, hsvRowHeader;
  private ScrollView svBody, svColumnHeader;

  public DataTable(Context context) {
    this(context, null);
  }

  public DataTable(Context context, AttributeSet attrs) {
    super(context, attrs);

    this.context = context;
    this.attributeSet = attrs;

    mainView = LayoutInflater.from(context).inflate(R.layout.data_table, null);
    addView(mainView);

    instantiate();
    initialize();
  }

  private void instantiate() {
    layoutCorner = findViewById(R.id.layoutCorner);
    layoutColumnHeader = findViewById(R.id.layoutColumnHeader);
    layoutRowHeader = findViewById(R.id.layoutRowHeader);
    layoutBody = findViewById(R.id.layoutBody);

    hsvRowHeader = layoutRowHeader.findViewById(R.id.horizontalScroll);
    hsvBody = layoutBody.findViewById(R.id.horizontalScroll);

    svBody = layoutColumnHeader.findViewById(R.id.scroll);
    svColumnHeader = layoutBody.findViewById(R.id.scroll);

    tlRowHeader = layoutRowHeader.findViewById(R.id.table);
    tlColumnHeader = layoutColumnHeader.findViewById(R.id.table);
    tlBody = layoutBody.findViewById(R.id.table);
  }

  private void initialize() {
  }

}
