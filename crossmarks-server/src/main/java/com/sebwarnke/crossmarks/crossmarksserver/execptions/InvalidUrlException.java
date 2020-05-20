package com.sebwarnke.crossmarks.crossmarksserver.execptions;

public class InvalidUrlException extends Exception {
  public InvalidUrlException(String url) {
    super("Invalid URL: " + url);
  }
}
