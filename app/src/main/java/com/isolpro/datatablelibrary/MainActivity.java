package com.isolpro.datatablelibrary;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private TableLayout rowHeader, colHeader, body;

  private RelativeLayout layoutCorner, layoutColHeader, layoutRowHeader, layoutBody;

  private HorizontalScrollView horizontalScrollBody, horizontalScrollRowHeader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    layoutCorner = findViewById(R.id.layoutCorner);
    layoutColHeader = findViewById(R.id.layoutColHeader);
    layoutRowHeader = findViewById(R.id.layoutRowHeader);
    layoutBody = findViewById(R.id.layoutBody);

    rowHeader = layoutRowHeader.findViewById(R.id.table);
    colHeader = layoutColHeader.findViewById(R.id.table);
    body = layoutBody.findViewById(R.id.table);

    body.post(this::syncBodyWidthWithRowHeader);
  }

  private void syncBodyWidthWithRowHeader() {
    Log.e("body_width: ", String.valueOf(body.getWidth()));
    Log.e("row_header_width: ", String.valueOf(rowHeader.getMeasuredWidth()));

    TableRow firstBodyRow = (TableRow) body.getChildAt(0);
    TableRow rowHeaderRow = (TableRow) rowHeader.getChildAt(0);

    for (int i = 0; i < firstBodyRow.getChildCount(); i++) {
      View bodyCell = firstBodyRow.getChildAt(i);
      View rowHeaderCell = rowHeaderRow.getChildAt(i);

      if (bodyCell.getMeasuredWidth() > rowHeaderCell.getMeasuredWidth())
        rowHeaderCell.setMinimumWidth(bodyCell.getMeasuredWidth());
      else
        bodyCell.setMinimumWidth(rowHeaderCell.getMeasuredWidth());
    }

    rowHeader.post(() -> {
      Log.e("body_width: ", String.valueOf(body.getWidth()));
      Log.e("row_header_width: ", String.valueOf(rowHeader.getMeasuredWidth()));
    });

  }
}