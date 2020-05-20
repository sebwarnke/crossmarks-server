package com.sebwarnke.crossmarks.crossmarksserver;

import com.sebwarnke.crossmarks.crossmarksserver.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

  private BookmarkService bookmarkService;

  @Autowired
  public Initializer(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @Override
  public void run(final String... args) throws Exception {

    Bookmark bookmarkTemplate = Bookmark.builder()
      .name("sebwarnke.com - my homepage")
      .url("https://sebwarnke.com")
      .build();

    bookmarkService.createBookmark(bookmarkTemplate);
  }
}
