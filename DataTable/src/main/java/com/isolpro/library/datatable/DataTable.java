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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class DataTable extends RelativeLayout {

  private final Context context;
  private final AttributeSet attributeSet;

  private final View mainView;
  private DataTableAdapter dataTableAdapter;
  private DataTableAdapterDataObserver adapterDataObserver;
  private OnBodyRowClickedListener onBodyRowClickedListener;

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

    adapterDataObserver = new DataTableAdapterDataObserver();
  }

  private void initialize() {
    syncScrolling();
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

    if (trBody == null || trRowHeader == null) return;

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

  private void populateCorner() {
    CornerTextView currentView = layoutCorner.getChildCount() > 0 ? (CornerTextView) layoutCorner.getChildAt(0) : null;
    layoutCorner.addView(dataTableAdapter.getCornerView(currentView));
  }

  private void populateTopHeader() {
    if (tlRowHeader.getChildCount() == 0)
      tlRowHeader.addView(new TableRow(context));

    TableRow thTr = ((TableRow) tlRowHeader.getChildAt(0));

    for (int i = 0; i < dataTableAdapter.getTopHeaderCount(); i++) {
      TopHeaderTextView currentView = thTr.getChildCount() > i ? (TopHeaderTextView) thTr.getChildAt(i) : null;
      thTr.addView(dataTableAdapter.getTopHeaderView(i, currentView));
    }

    // TODO: remove any extras view that may have been created

    tlBody.post(this::remeasureBodyWithRowHeader);
  }

  private void populateStartHeader() {
//    for (int i = 0; i < dataTableAdapter.getStartHeaderCount(); i++) {
//      TopHeaderTextView currentView = tlColumnHeader.getChildCount() > i ? (TopHeaderTextView) tlColumnHeader.getChildAt(i) : null;
//    }

//    if (columnHeaderTexts == null) return;
//
//    tlColumnHeader.removeAllViews();
//
//    for (String columnHeaderText : columnHeaderTexts) {
//      StartHeaderTextView textView = dataTableAdapter.getStartHeaderView();
//
//      if (textView == null)
//        throw new DataTableError("TopHeaderTextView cannot be null for DataTableAdapter!");
//
//      textView.setText(columnHeaderText);
//
//      TableRow tableRow = new TableRow(context);
//      tableRow.addView(textView);
//
//      tlColumnHeader.addView(tableRow);
//    }

//    tlBody.post(this::remeasureBodyWithColumnHeader);
  }

  private void populateBody() {
//    if (bodyTextsList == null) return;
//
//    tlBody.removeAllViews();
//
//    for (List<String> bodyTexts : bodyTextsList) {
//      TableRow tr = dataTableAdapter.getBodyView(bodyTexts);
//
//      tr.setOnClickListener(this::handleOnBodyRowClicked);
//
//      tlBody.addView(tr);
//    }
  }

  private void handleOnBodyRowClicked(View rowView) {
    if (onBodyRowClickedListener != null)
      onBodyRowClickedListener.onClicked((TableRow) rowView);
  }

  public void setAdapter(DataTableAdapter dataTableAdapter) {
    this.dataTableAdapter = dataTableAdapter;
    dataTableAdapter.setAdapterDataObserver(adapterDataObserver);

//    dataTableAdapter.setOnDatasetChangedListener((cornerText, rowHeaderTexts, columnHeaderTexts, bodyTextsList) -> {
//      populateCorner(cornerText);
//      populateTopHeader(rowHeaderTexts);
//      populateStartHeader(columnHeaderTexts);
//      populateBody(bodyTextsList);
//    });
  }

  public void setCornerViewBackgroundColor(int color) {
    layoutCorner.setBackgroundColor(color);
  }

  public void setRowHeaderBackgroundColor(int color) {
    tlRowHeader.setBackgroundColor(color);
  }

  public void setColumnHeaderBackgroundColor(int color) {
    tlColumnHeader.setBackgroundColor(color);
  }

  public void setOnBodyRowClickedListener(OnBodyRowClickedListener onBodyRowClickedListener) {
    this.onBodyRowClickedListener = onBodyRowClickedListener;
  }

  public interface OnBodyRowClickedListener {
    void onClicked(TableRow tableRow);
  }

  private class DataTableAdapterDataObserver extends RecyclerView.AdapterDataObserver {

    @Override
    public void onChanged() {
      if (dataTableAdapter != null) {
        populateCorner();
        populateTopHeader();
        populateStartHeader();
        populateBody();
      }
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
      super.onItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
      super.onItemRangeChanged(positionStart, itemCount, payload);
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
      super.onItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
      super.onItemRangeRemoved(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
      super.onItemRangeMoved(fromPosition, toPosition, itemCount);
    }
  }

}
