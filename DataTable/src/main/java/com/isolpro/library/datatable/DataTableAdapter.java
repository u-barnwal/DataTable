package com.isolpro.library.datatable;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public abstract class DataTableAdapter {

  protected final Context context;

  protected String cornerText;
  protected List<String> rowHeaderTexts, columnHeaderTexts;

  public DataTableAdapter(Context context) {
    this.context = context;
  }

  @NonNull
  protected abstract CornerTextView onCreateCornerView(ViewGroup parent);

  @NonNull
  protected abstract RowHeaderTextView onCreateRowHeaderView(ViewGroup parent);

  @NonNull
  protected abstract ColumnHeaderTextView onCreateColumnHeaderView(ViewGroup parent);

  protected abstract String onPopulateCornerView();

  protected abstract List<String> onPopulateRowHeaderView();

  protected abstract List<String> onPopulateColumnHeaderView();

}
