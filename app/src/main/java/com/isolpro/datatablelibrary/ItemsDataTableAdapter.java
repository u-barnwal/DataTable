package com.isolpro.datatablelibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.isolpro.library.datatable.BodyTableRow;
import com.isolpro.library.datatable.CornerTextView;
import com.isolpro.library.datatable.DataTableAdapter;
import com.isolpro.library.datatable.StartHeaderTextView;
import com.isolpro.library.datatable.TopHeaderTextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataTableAdapter extends DataTableAdapter<String> {

  private final Context context;

  private final String cornerItem = "#";
  private final List<String> topHeaderItems = new ArrayList<>();
  private final List<String> startHeaderItems = new ArrayList<>();
  private final List<List<String>> bodyItems = new ArrayList<>();

  public ItemsDataTableAdapter(Context context) {
    this.context = context;

    // + Top Header
    topHeaderItems.add("Name");
    topHeaderItems.add("Class");
    topHeaderItems.add("Section");
    topHeaderItems.add("Roll");
    topHeaderItems.add("Email");
    topHeaderItems.add("Mobile");
    topHeaderItems.add("Website");
    topHeaderItems.add("Subject");

    // + Start Header
    startHeaderItems.add("1");
    startHeaderItems.add("2");
    startHeaderItems.add("3");
    startHeaderItems.add("4");
    startHeaderItems.add("5");
    startHeaderItems.add("6");
    startHeaderItems.add("7");
    startHeaderItems.add("8");
    startHeaderItems.add("9");
    startHeaderItems.add("10");
    startHeaderItems.add("11");
    startHeaderItems.add("12");
    startHeaderItems.add("13");
    startHeaderItems.add("14");
    startHeaderItems.add("15");
    startHeaderItems.add("16");
    startHeaderItems.add("17");
    startHeaderItems.add("18");
    startHeaderItems.add("19");
    startHeaderItems.add("20");
    startHeaderItems.add("21");
    startHeaderItems.add("22");
    startHeaderItems.add("23");
    startHeaderItems.add("24");
    startHeaderItems.add("25");

    // + Body

    List<String> strings = new ArrayList<>();

    strings.add("John Doe");
    strings.add("12th");
    strings.add("A");
    strings.add("21");
    strings.add("johndoe@gmail.com");
    strings.add("9555666555");
    strings.add("www.johndoe.com");
    strings.add("English");

    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
    bodyItems.add(strings);
  }

  @Override
  public int getTopHeaderCount() {
    return topHeaderItems.size();
  }

  @Override
  public int getStartHeaderCount() {
    return startHeaderItems.size();
  }

  @Override
  public int getBodyCount() {
    return bodyItems.size();
  }

  @Override
  public String getCornerItem(int position) {
    return cornerItem;
  }

  @Override
  public String getTopHeaderItem(int position) {
    return topHeaderItems.get(position);
  }

  @Override
  public String getStartHeaderItem(int position) {
    return startHeaderItems.get(position);
  }

  @Override
  public List<String> getBodyItem(int position) {
    return bodyItems.get(position);
  }

  @NonNull
  @Override
  protected CornerTextView getCornerView(int position, CornerTextView view) {
    CornerTextView cornerTextView = view != null
      ? view
      : (CornerTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_corner, null);

    cornerTextView.setText(getCornerItem(position));

    return cornerTextView;
  }

  @NonNull
  @Override
  protected TopHeaderTextView getTopHeaderView(int position, TopHeaderTextView view) {
    TopHeaderTextView topHeaderTextView = view != null
      ? view
      : (TopHeaderTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_top_header, null);

    topHeaderTextView.setText(getTopHeaderItem(position));

    return topHeaderTextView;
  }

  @NonNull
  @Override
  protected StartHeaderTextView getStartHeaderView(int position, StartHeaderTextView view) {
    StartHeaderTextView startHeaderTextView = view != null
      ? view
      : (StartHeaderTextView) LayoutInflater.from(context).inflate(R.layout.layout_data_table_start_header, null);

    startHeaderTextView.setText(getStartHeaderItem(position));

    return startHeaderTextView;
  }

  @NonNull
  @Override
  protected BodyTableRow getBodyView(int position, BodyTableRow view) {
    BodyTableRow bodyTableRow = view != null
      ? view
      : (BodyTableRow) LayoutInflater.from(context).inflate(R.layout.layout_data_table_body, null);

    ((TextView) bodyTableRow.findViewById(R.id.name)).setText(getBodyItem(position).get(0));
    ((TextView) bodyTableRow.findViewById(R.id.classText)).setText(getBodyItem(position).get(1));
    ((TextView) bodyTableRow.findViewById(R.id.section)).setText(getBodyItem(position).get(2));
    ((TextView) bodyTableRow.findViewById(R.id.roll)).setText(getBodyItem(position).get(3));
    ((TextView) bodyTableRow.findViewById(R.id.email)).setText(getBodyItem(position).get(4));
    ((TextView) bodyTableRow.findViewById(R.id.mobile)).setText(getBodyItem(position).get(5));
    ((TextView) bodyTableRow.findViewById(R.id.website)).setText(getBodyItem(position).get(6));
    ((TextView) bodyTableRow.findViewById(R.id.subject)).setText(getBodyItem(position).get(7));

    bodyTableRow.setTag("1");

    return bodyTableRow;
  }
}
