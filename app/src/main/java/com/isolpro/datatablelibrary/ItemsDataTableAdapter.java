package com.isolpro.datatablelibrary;

import android.content.Context;
import android.view.LayoutInflater;

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
  protected CornerTextView onCreateCornerView() {
    return (CornerTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_corner, null);
  }

  @NonNull
  @Override
  protected RowHeaderTextView onCreateRowHeaderView() {
    return (RowHeaderTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_row_header, null);
  }

  @NonNull
  @Override
  protected ColumnHeaderTextView onCreateColumnHeaderView() {
    return (ColumnHeaderTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_column_header, null);
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
    strings.add("Roll");
    strings.add("Email");
    strings.add("Mobile");
    strings.add("Website");
    strings.add("Subject");

    return strings;
  }

  @Override
  protected List<String> onPopulateColumnHeaderView() {
    List<String> strings = new ArrayList<>();

    strings.add("1");
    strings.add("2");
    strings.add("3");
    strings.add("4");
    strings.add("5");
    strings.add("6");
    strings.add("7");
    strings.add("8");
    strings.add("9");
    strings.add("10");
    strings.add("11");
    strings.add("12");
    strings.add("13");
    strings.add("14");
    strings.add("15");
    strings.add("16");
    strings.add("17");
    strings.add("18");
    strings.add("19");
    strings.add("20");
    strings.add("21");
    strings.add("22");
    strings.add("23");
    strings.add("24");
    strings.add("25");

    return strings;
  }
}
