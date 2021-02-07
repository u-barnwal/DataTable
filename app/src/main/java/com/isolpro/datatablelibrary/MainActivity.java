package com.isolpro.datatablelibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.isolpro.library.datatable.DataTable;

public class MainActivity extends AppCompatActivity {

  private DataTable dataTable;
  private ItemsDataTableAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    dataTable = findViewById(R.id.datatable);
    adapter = new ItemsDataTableAdapter(this);

    dataTable.setCornerViewBackgroundColor(getResources().getColor(R.color.white_10));
    dataTable.setRowHeaderBackgroundColor(getResources().getColor(R.color.white_10));
    dataTable.setColumnHeaderBackgroundColor(getResources().getColor(R.color.white_10));

    dataTable.setAdapter(adapter);

    adapter.notifyDatasetChange();
  }

}