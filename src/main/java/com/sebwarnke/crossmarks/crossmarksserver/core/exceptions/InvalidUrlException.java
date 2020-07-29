package com.sebwarnke.crossmarks.crossmarksserver.core.exceptions;

public class InvalidUrlException extends Exception {
  public InvalidUrlException(String url) {
    super("Invalid Bookmark URL: " + url);
  }
}
