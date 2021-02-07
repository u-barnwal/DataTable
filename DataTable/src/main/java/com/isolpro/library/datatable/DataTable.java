package com.isolpro.library.datatable;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.RequiresApi;

import java.util.List;

public class DataTable extends RelativeLayout {

  private final Context context;
  private final AttributeSet attributeSet;

  private final View mainView;
  private DataTableAdapter dataTableAdapter;

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
    syncScrolling();

    tlBody.post(() -> {
      remeasureBodyWithRowHeader();
      remeasureBodyWithColumnHeader();
    });
  }

  private void syncScrolling() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      syncBodyScrollWithRowHeaderScroll();
      syncBodyScrollWithColHeaderScroll();
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void syncBodyScrollWithRowHeaderScroll() {
    hsvBody.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      hsvRowHeader.setScrollX(scrollX);
      hsvRowHeader.setScrollY(scrollY);
    });

    hsvRowHeader.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      hsvBody.setScrollX(scrollX);
      hsvBody.setScrollY(scrollY);
    });
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void syncBodyScrollWithColHeaderScroll() {
    svBody.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      svColumnHeader.setScrollX(scrollX);
      svColumnHeader.setScrollY(scrollY);
    });

    svColumnHeader.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      svBody.setScrollX(scrollX);
      svBody.setScrollY(scrollY);
    });
  }

  private void remeasureBodyWithRowHeader() {
    TableRow trBody = (TableRow) tlBody.getChildAt(0);
    TableRow trRowHeader = (TableRow) tlRowHeader.getChildAt(0);

    for (int i = 0; i < trBody.getChildCount(); i++) {
      View cellBody = trBody.getChildAt(i);
      View cellRowHeader = trRowHeader.getChildAt(i);

      if (cellBody.getMeasuredWidth() > cellRowHeader.getMeasuredWidth())
        cellRowHeader.setMinimumWidth(cellBody.getMeasuredWidth());
      else
        cellBody.setMinimumWidth(cellRowHeader.getMeasuredWidth());
    }
  }

  private void remeasureBodyWithColumnHeader() {
    for (int i = 0; i < tlBody.getChildCount(); i++) {
      View columnBody = tlBody.getChildAt(i);
      View columnColumnHeader = tlColumnHeader.getChildAt(i);

      if (columnBody.getMeasuredHeight() > columnColumnHeader.getMeasuredHeight())
        columnColumnHeader.setMinimumHeight(columnBody.getMeasuredHeight());
      else
        columnBody.setMinimumHeight(columnColumnHeader.getMeasuredHeight());
    }
  }

  private void populateCorner(String cornerText) {
    CornerTextView textView = dataTableAdapter.onCreateCornerView();

    if (textView == null)
      throw new DataTableError("CornerTextView cannot be null for DataTableAdapter!");

    textView.setText(cornerText);

    layoutCorner.addView(textView);
  }

  private void populateRowHeader(List<String> rowHeaderTexts) {
    if (rowHeaderTexts == null) return;

    for (String rowHeaderText : rowHeaderTexts) {
      RowHeaderTextView textView = dataTableAdapter.onCreateRowHeaderView();

      if (textView == null)
        throw new DataTableError("RowHeaderTextView cannot be null for DataTableAdapter!");

      // * Creating row if doesn't already have
      if (tlRowHeader.getChildCount() == 0)
        tlRowHeader.addView(new TableRow(context));

      textView.setText(rowHeaderText);

      ((TableRow) tlRowHeader.getChildAt(0)).addView(textView);
    }
  }

  private void populateColumnHeader(List<String> columnHeaderTexts) {
    if (columnHeaderTexts == null) return;

    for (String columnHeaderText : columnHeaderTexts) {
      ColumnHeaderTextView textView = dataTableAdapter.onCreateColumnHeaderView();

      if (textView == null)
        throw new DataTableError("RowHeaderTextView cannot be null for DataTableAdapter!");

      textView.setText(columnHeaderText);

      TableRow tableRow = new TableRow(context);
      tableRow.addView(textView);

      tlColumnHeader.addView(tableRow);
    }
  }

  private void populateBody(List<List<String>> bodyTextsList) {
    if (bodyTextsList == null) return;

    for (List<String> bodyTexts : bodyTextsList) {
      tlBody.addView(dataTableAdapter.onCreateBodyView(bodyTexts));
    }
  }

  public void setAdapter(DataTableAdapter dataTableAdapter) {
    this.dataTableAdapter = dataTableAdapter;

    dataTableAdapter.setOnDatasetChangeListener((cornerText, rowHeaderTexts, columnHeaderTexts, bodyTextsList) -> {
      populateCorner(cornerText);
      populateRowHeader(rowHeaderTexts);
      populateColumnHeader(columnHeaderTexts);
      populateBody(bodyTextsList);
    });
  }

}
