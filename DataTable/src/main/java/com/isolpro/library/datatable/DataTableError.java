package com.isolpro.library.datatable;

public class DataTableError extends Error {

  public DataTableError(String message) {
    super(message);
  }

  public DataTableError(String message, Throwable cause) {
    super(message, cause);
  }
}
