package com.isolpro.datatablelibrary;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.isolpro.library.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

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

    dataTable.setOnBodyRowClickedListener(tableRow -> Log.e("onClicked----: ", (String) tableRow.getTag()));

    dataTable.setAdapter(adapter);

    adapter.notifyDatasetChanged();

    adapter.setCornerItem("*");

    List<String> topHeaderItems = new ArrayList<>();
    topHeaderItems.add("Names");
    topHeaderItems.add("Classes");
    topHeaderItems.add("Sections");
    topHeaderItems.add("Rolls");
    topHeaderItems.add("Emails");
    topHeaderItems.add("Mobiles");
    topHeaderItems.add("Websites");
    topHeaderItems.add("Subjects");

    adapter.setTopHeaderItems(topHeaderItems);

    List<String> startHeaderItems = new ArrayList<>();
    startHeaderItems.add("a");
    startHeaderItems.add("b");
    startHeaderItems.add("c");
    startHeaderItems.add("d");
    startHeaderItems.add("e");
    startHeaderItems.add("f");
    startHeaderItems.add("g");
    startHeaderItems.add("h");

    adapter.setStartHeaderItems(startHeaderItems);

    adapter.notifyDatasetChanged();
  }

}