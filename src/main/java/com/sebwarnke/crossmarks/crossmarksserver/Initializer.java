package com.sebwarnke.crossmarks.crossmarksserver;

import com.sebwarnke.crossmarks.crossmarksserver.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.model.entities.User;
import com.sebwarnke.crossmarks.crossmarksserver.model.repositories.UserRepository;
import com.sebwarnke.crossmarks.crossmarksserver.services.BookmarkService;
import com.sebwarnke.crossmarks.crossmarksserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

  private BookmarkService bookmarkService;
  private UserService userService;

  @Autowired
  public Initializer(BookmarkService bookmarkService, UserService userService) {
    this.bookmarkService = bookmarkService;
    this.userService = userService;
  }

  @Override
  public void run(final String... args) throws Exception {

    Bookmark bookmarkTemplate = Bookmark.builder()
      .name("sebwarnke.com - my homepage")
      .url("https://sebwarnke.com")
      .build();

    User user = User.builder()
      .username("demo")
      .password("demo")
      .build();

    userService.createUser(user);
    bookmarkService.createBookmark(bookmarkTemplate);
  }
}
