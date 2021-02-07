package com.isolpro.datatablelibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.isolpro.library.datatable.ColumnHeaderTextView;
import com.isolpro.library.datatable.CornerTextView;
import com.isolpro.library.datatable.DataTableAdapter;
import com.isolpro.library.datatable.RowHeaderTextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataTableAdapter extends DataTableAdapter {

  public ItemsDataTableAdapter(Context context) {
    super(context);
  }

  @NonNull
  @Override
  protected CornerTextView onCreateCornerView(ViewGroup parent) {
    return (CornerTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_corner, parent);
  }

  @NonNull
  @Override
  protected RowHeaderTextView onCreateRowHeaderView(ViewGroup parent) {
    return (RowHeaderTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_corner, parent);
  }

  @NonNull
  @Override
  protected ColumnHeaderTextView onCreateColumnHeaderView(ViewGroup parent) {
    return (ColumnHeaderTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_corner, parent);
  }

  @Override
  protected String onPopulateCornerView() {
    return "#";
  }

  @Override
  protected List<String> onPopulateRowHeaderView() {
    List<String> strings = new ArrayList<>();

    strings.add("Name");
    strings.add("Class");
    strings.add("Section");

    return strings;
  }

  @Override
  protected List<String> onPopulateColumnHeaderView() {
    List<String> strings = new ArrayList<>();

    strings.add("1");
    strings.add("2");
    strings.add("3");

    return strings;
  }
}
