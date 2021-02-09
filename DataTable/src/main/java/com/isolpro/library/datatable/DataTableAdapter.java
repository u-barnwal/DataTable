package com.isolpro.library.datatable;

import androidx.annotation.NonNull;

public abstract class DataTableAdapter<BI> {

  private DataTable.AdapterDataObserver adapterDataObserver;

  public int getTopHeaderCount() {
    return 0;
  }

  public int getStartHeaderCount() {
    return 0;
  }

  public int getBodyCount() {
    return 0;
  }

  public String getCornerItem() {
    return null;
  }

  public String getTopHeaderItem(int position) {
    return null;
  }

  public String getStartHeaderItem(int position) {
    return null;
  }

  public BI getBodyItem(int position) {
    return null;
  }

  @NonNull
  protected abstract CornerTextView getCornerView(CornerTextView view);

  @NonNull
  protected abstract TopHeaderTextView getTopHeaderView(int position, TopHeaderTextView view);

  @NonNull
  protected abstract StartHeaderTextView getStartHeaderView(int position, StartHeaderTextView view);

  @NonNull
  protected abstract BodyTableRow getBodyView(int position, BodyTableRow view);

  public void notifyDatasetChanged(DataSet dataSet) {
    if (adapterDataObserver != null)
      adapterDataObserver.onChanged(dataSet);
  }

  public void notifyDatasetChanged() {
    notifyDatasetChanged(null);
  }

  void setAdapterDataObserver(DataTable.AdapterDataObserver adapterDataObserver) {
    this.adapterDataObserver = adapterDataObserver;
  }

  public enum DataSet {
    Corner, TopHeader, StartHeader, Body
  }

}
