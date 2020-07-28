package com.sebwarnke.crossmarks.crossmarksserver.core.util;

import java.util.regex.Pattern;

public class Util {

  public static Boolean isValidUrl(String url) {
    return Pattern.compile("https?://(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9" +
                             "()@:%_\\+.~#?&//=]*)")
      .matcher(url)
      .matches();
  }
}
