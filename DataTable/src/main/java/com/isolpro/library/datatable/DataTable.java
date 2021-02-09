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
  private AdapterDataObserver adapterDataObserver;
  private OnBodyRowClickedListener onBodyRowClickedListener;

  private TableLayout tlTopHeader, tlStartHeader, tlBody;

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

    tlTopHeader = layoutRowHeader.findViewById(R.id.table);
    tlStartHeader = layoutColumnHeader.findViewById(R.id.table);
    tlBody = layoutBody.findViewById(R.id.table);

    adapterDataObserver = new AdapterDataObserver();
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

  private void remeasureBodyWithTopHeader() {
    TableRow trBody = (TableRow) tlBody.getChildAt(0);
    TableRow trTopHeader = (TableRow) tlTopHeader.getChildAt(0);

    if (trBody == null || trTopHeader == null) return;

    for (int i = 0; i < trBody.getChildCount(); i++) {
      View cellBody = trBody.getChildAt(i);
      View cellRowHeader = trTopHeader.getChildAt(i);

      if (cellBody == null || cellRowHeader == null) return;

      if (cellBody.getMeasuredWidth() > cellRowHeader.getMeasuredWidth())
        cellRowHeader.setMinimumWidth(cellBody.getMeasuredWidth());
      else
        cellBody.setMinimumWidth(cellRowHeader.getMeasuredWidth());
    }
  }

  private void remeasureBodyWithStartHeader() {
    for (int i = 0; i < tlBody.getChildCount(); i++) {
      View columnBody = tlBody.getChildAt(i);
      View columnStartHeader = tlStartHeader.getChildAt(i);

      if (columnBody == null || columnStartHeader == null) return;

      if (columnBody.getMeasuredHeight() > columnStartHeader.getMeasuredHeight())
        columnStartHeader.setMinimumHeight(columnBody.getMeasuredHeight());
      else
        columnBody.setMinimumHeight(columnStartHeader.getMeasuredHeight());
    }
  }

  private void populateCorner() {
    CornerTextView currentView = layoutCorner.getChildCount() > 0
      ? (CornerTextView) layoutCorner.getChildAt(0)
      : null;

    layoutCorner.removeAllViews();

    layoutCorner.addView(dataTableAdapter.getCornerView(currentView));
  }

  private void populateTopHeader() {
    int itemsCount = dataTableAdapter.getTopHeaderCount();

    if (tlTopHeader.getChildCount() == 0)
      tlTopHeader.addView(new TableRow(context));

    TableRow thTr = ((TableRow) tlTopHeader.getChildAt(0));

    for (int i = 0; i < itemsCount; i++) {
      if (thTr.getChildCount() > i) {
        TopHeaderTextView currentView = (TopHeaderTextView) thTr.getChildAt(i);

        thTr.removeViewAt(i);
        thTr.addView(dataTableAdapter.getTopHeaderView(i, currentView), i);
      } else
        thTr.addView(dataTableAdapter.getTopHeaderView(i, null));
    }

    Utils.removeChildFromIndex(thTr, itemsCount);
  }

  private void populateStartHeader() {
    int itemsCount = dataTableAdapter.getStartHeaderCount();

    for (int i = 0; i < dataTableAdapter.getStartHeaderCount(); i++) {
      if (tlStartHeader.getChildCount() > i) {
        TableRow shTr = (TableRow) tlStartHeader.getChildAt(i);

        StartHeaderTextView currentView = (StartHeaderTextView) shTr.getChildAt(0);

        shTr.removeAllViews();
        shTr.addView(dataTableAdapter.getStartHeaderView(i, currentView));
      } else {
        TableRow shTr = new TableRow(context);
        shTr.addView(dataTableAdapter.getStartHeaderView(i, null));

        tlStartHeader.addView(shTr);
      }

      Utils.removeChildFromIndex(tlStartHeader, itemsCount);
    }
  }

  private void populateBody() {
    int itemsCount = dataTableAdapter.getBodyCount();

    for (int i = 0; i < itemsCount; i++) {
      if (tlBody.getChildCount() > i) {
        BodyTableRow currentView = (BodyTableRow) tlBody.getChildAt(i);

        tlBody.removeViewAt(i);
        tlBody.addView(dataTableAdapter.getBodyView(i, currentView), i);
      } else
        tlBody.addView(dataTableAdapter.getBodyView(i, null));
    }

    Utils.removeChildFromIndex(tlBody, itemsCount);
  }

  private void remeasureDimensions(@Nullable DataTableAdapter.DataSet dataSet) {
    if (dataSet != null) {
      switch (dataSet) {
        case Corner:
          return;
        case TopHeader:
          remeasureBodyWithTopHeader();
          break;
        case StartHeader:
          remeasureBodyWithStartHeader();
          break;
        case Body:
        default:
          remeasureBodyWithTopHeader();
          remeasureBodyWithStartHeader();
      }
    } else {
      remeasureBodyWithTopHeader();
      remeasureBodyWithStartHeader();
    }
  }

  private void handleOnBodyRowClicked(View rowView) {
    if (onBodyRowClickedListener != null)
      onBodyRowClickedListener.onClicked((TableRow) rowView);
  }

  public void setAdapter(DataTableAdapter dataTableAdapter) {
    this.dataTableAdapter = dataTableAdapter;
    dataTableAdapter.setAdapterDataObserver(adapterDataObserver);
  }

  public void setCornerViewBackgroundColor(int color) {
    layoutCorner.setBackgroundColor(color);
  }

  public void setRowHeaderBackgroundColor(int color) {
    tlTopHeader.setBackgroundColor(color);
  }

  public void setColumnHeaderBackgroundColor(int color) {
    tlStartHeader.setBackgroundColor(color);
  }

  public void setOnBodyRowClickedListener(OnBodyRowClickedListener onBodyRowClickedListener) {
    this.onBodyRowClickedListener = onBodyRowClickedListener;
  }

  public interface OnBodyRowClickedListener {
    void onClicked(TableRow tableRow);
  }

  class AdapterDataObserver extends RecyclerView.AdapterDataObserver {

    @Override
    public void onChanged() {
    }

    public void onChanged(DataTableAdapter.DataSet dataSet) {
      if (dataTableAdapter == null) return;

      if (dataSet != null) {
        switch (dataSet) {
          case Corner:
            populateCorner();
            break;
          case TopHeader:
            populateTopHeader();
            break;
          case StartHeader:
            populateStartHeader();
            break;
          case Body:
            populateBody();
            break;
        }
      } else {
        populateBody();
        populateStartHeader();
        populateTopHeader();
        populateCorner();
      }

      tlBody.post(() -> remeasureDimensions(dataSet));
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
    }
  }

}
