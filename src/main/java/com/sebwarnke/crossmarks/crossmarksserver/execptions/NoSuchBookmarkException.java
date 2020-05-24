package com.sebwarnke.crossmarks.crossmarksserver.execptions;

public class NoSuchBookmarkException extends Exception {
  public NoSuchBookmarkException(String id) {
    super("No Bookmark found for ID [" + id + "]");
  }
}
