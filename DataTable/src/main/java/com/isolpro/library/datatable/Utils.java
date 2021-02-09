package com.isolpro.library.datatable;

import android.widget.LinearLayout;

final class Utils {

  public static void removeChildFromIndex(LinearLayout parentView, int index) {
    int size = parentView.getChildCount();

    if (index > 0 && index < size) {
      int removeCount = size - index;
      parentView.removeViews(index, removeCount);
    }
  }

}
