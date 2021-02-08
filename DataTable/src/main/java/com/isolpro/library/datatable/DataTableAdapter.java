package com.isolpro.library.datatable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class DataTableAdapter<BI> {

  private RecyclerView.AdapterDataObserver adapterDataObserver;

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

  public List<BI> getBodyItem(int position) {
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

  public final void notifyDatasetChanged() {
    if (adapterDataObserver != null)
      adapterDataObserver.onChanged();
  }

  public void setAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
    this.adapterDataObserver = adapterDataObserver;
  }
}
