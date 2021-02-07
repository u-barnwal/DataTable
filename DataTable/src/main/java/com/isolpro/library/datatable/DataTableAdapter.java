package com.isolpro.library.datatable;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

public abstract class DataTableAdapter {

  protected final Context context;
  protected String cornerText;
  protected List<String> rowHeaderTexts, columnHeaderTexts;
  private OnDatasetChangeListener onDatasetChangeListener;

  public DataTableAdapter(Context context) {
    this.context = context;

    cornerText = onPopulateCornerView();
    rowHeaderTexts = onPopulateRowHeaderView();
    columnHeaderTexts = onPopulateColumnHeaderView();

    notifyDatasetChange();
  }

  @NonNull
  protected abstract CornerTextView onCreateCornerView();

  @NonNull
  protected abstract RowHeaderTextView onCreateRowHeaderView();

  @NonNull
  protected abstract ColumnHeaderTextView onCreateColumnHeaderView();

  protected abstract String onPopulateCornerView();

  protected abstract List<String> onPopulateRowHeaderView();

  protected abstract List<String> onPopulateColumnHeaderView();

  public final void notifyDatasetChange() {
    if (onDatasetChangeListener != null)
      onDatasetChangeListener.exec(cornerText, rowHeaderTexts, columnHeaderTexts);
  }

  final void setOnDatasetChangeListener(OnDatasetChangeListener onDatasetChangeListener) {
    this.onDatasetChangeListener = onDatasetChangeListener;
  }

  interface OnDatasetChangeListener {
    void exec(String cornerText, List<String> rowHeaderTexts, List<String> columnHeaderTexts);
  }
}
