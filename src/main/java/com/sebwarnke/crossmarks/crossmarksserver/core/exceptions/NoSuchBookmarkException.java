package com.sebwarnke.crossmarks.crossmarksserver.core.exceptions;

public class NoSuchBookmarkException extends Exception {
  public NoSuchBookmarkException(String id) {
    super("No Bookmark found for ID [" + id + "]");
  }
}
