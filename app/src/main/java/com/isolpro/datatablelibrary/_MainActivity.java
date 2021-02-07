package com.isolpro.datatablelibrary;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class _MainActivity extends AppCompatActivity {

  private TableLayout rowHeader, colHeader, body;

  private RelativeLayout layoutCorner, layoutColHeader, layoutRowHeader, layoutBody;

  private HorizontalScrollView horizontalScrollBody, horizontalScrollRowHeader;
  private ScrollView scrollBody, scrollColHeader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    layoutCorner = findViewById(R.id.layoutCorner);
    layoutColHeader = findViewById(R.id.layoutColumnHeader);
    layoutRowHeader = findViewById(R.id.layoutRowHeader);
    layoutBody = findViewById(R.id.layoutBody);

    horizontalScrollRowHeader = layoutRowHeader.findViewById(R.id.horizontalScroll);
    horizontalScrollBody = layoutBody.findViewById(R.id.horizontalScroll);

    scrollBody = layoutColHeader.findViewById(R.id.scroll);
    scrollColHeader = layoutBody.findViewById(R.id.scroll);

    rowHeader = layoutRowHeader.findViewById(R.id.table);
    colHeader = layoutColHeader.findViewById(R.id.table);
    body = layoutBody.findViewById(R.id.table);

    body.post(this::syncBodyWidthWithRowHeader);
    body.post(this::syncBodyHeightWithColHeader);

    syncBodyScrollWithRowHeaderScroll();
    syncBodyScrollWithColHeaderScroll();
  }

  private void syncBodyWidthWithRowHeader() {
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
  }

  private void syncBodyHeightWithColHeader() {
    for (int i = 0; i < body.getChildCount(); i++) {
      View bodyColumn = body.getChildAt(i);
      View colHeaderColumn = colHeader.getChildAt(i);

      if (bodyColumn.getMeasuredHeight() > colHeaderColumn.getMeasuredHeight())
        colHeaderColumn.setMinimumHeight(bodyColumn.getMeasuredHeight());
      else
        bodyColumn.setMinimumHeight(colHeaderColumn.getMeasuredHeight());
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void syncBodyScrollWithRowHeaderScroll() {
    horizontalScrollBody.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      horizontalScrollRowHeader.setScrollX(scrollX);
      horizontalScrollRowHeader.setScrollY(scrollY);
    });

    horizontalScrollRowHeader.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      horizontalScrollBody.setScrollX(scrollX);
      horizontalScrollBody.setScrollY(scrollY);
    });
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void syncBodyScrollWithColHeaderScroll() {
    scrollBody.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      scrollColHeader.setScrollX(scrollX);
      scrollColHeader.setScrollY(scrollY);
    });

    scrollColHeader.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      scrollBody.setScrollX(scrollX);
      scrollBody.setScrollY(scrollY);
    });
  }

}