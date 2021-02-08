package com.isolpro.library.datatable;

import android.content.Context;
import android.widget.TableRow;

import androidx.annotation.NonNull;

import java.util.List;

public abstract class DataTableAdapter {

  protected final Context context;
  private OnDatasetChangedListener onDatasetChangedListener;

  public DataTableAdapter(Context context) {
    this.context = context;

    notifyDatasetChanged();
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

  public final void notifyDatasetChanged() {
    if (onDatasetChangedListener != null)
      onDatasetChangedListener.exec(onPopulateCornerView(), onPopulateRowHeaderView(), onPopulateColumnHeaderView(), onPopulateBodyView());
  }

  final void setOnDatasetChangedListener(OnDatasetChangedListener onDatasetChangedListener) {
    this.onDatasetChangedListener = onDatasetChangedListener;
  }

  interface OnDatasetChangedListener {
    void exec(String cornerText, List<String> rowHeaderTexts, List<String> columnHeaderTexts, List<List<String>> bodyTextsList);
  }
}
