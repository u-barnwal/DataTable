package com.isolpro.library.datatable;

import android.content.Context;
import android.widget.TableRow;

import androidx.annotation.NonNull;

import java.util.List;

public abstract class DataTableAdapter {

  protected final Context context;
  protected String cornerText;
  protected List<String> rowHeaderTexts, columnHeaderTexts;
  protected List<List<String>> bodyTextsList;
  private OnDatasetChangeListener onDatasetChangeListener;

  public DataTableAdapter(Context context) {
    this.context = context;

    cornerText = onPopulateCornerView();
    rowHeaderTexts = onPopulateRowHeaderView();
    columnHeaderTexts = onPopulateColumnHeaderView();
    bodyTextsList = onPopulateBodyView();

    notifyDatasetChange();
  }

  @NonNull
  protected abstract CornerTextView onCreateCornerView();

  @NonNull
  protected abstract RowHeaderTextView onCreateRowHeaderView();

  @NonNull
  protected abstract ColumnHeaderTextView onCreateColumnHeaderView();

  @NonNull
  protected abstract TableRow onCreateBodyView(List<String> bodyTexts);

  protected abstract String onPopulateCornerView();

  protected abstract List<String> onPopulateRowHeaderView();

  protected abstract List<String> onPopulateColumnHeaderView();

  protected abstract List<List<String>> onPopulateBodyView();

  public final void notifyDatasetChange() {
    if (onDatasetChangeListener != null)
      onDatasetChangeListener.exec(cornerText, rowHeaderTexts, columnHeaderTexts,bodyTextsList);
  }

  final void setOnDatasetChangeListener(OnDatasetChangeListener onDatasetChangeListener) {
    this.onDatasetChangeListener = onDatasetChangeListener;
  }

  interface OnDatasetChangeListener {
    void exec(String cornerText, List<String> rowHeaderTexts, List<String> columnHeaderTexts,List<List<String>> bodyTextsList);
  }
}
