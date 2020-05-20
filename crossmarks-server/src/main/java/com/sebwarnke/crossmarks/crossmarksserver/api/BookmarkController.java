package com.sebwarnke.crossmarks.crossmarksserver.api;

import com.sebwarnke.crossmarks.crossmarksserver.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BookmarkController {

  private BookmarkService bookmarkService;

  @Autowired
  public BookmarkController(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @GetMapping("/bookmark")
  public Collection<Bookmark> getAllBookmarks() {
    return bookmarkService.getAllBookmarks();
  }

  @PostMapping("/bookmark")
  public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
    return bookmarkService.createBookmark(bookmark);
  }
}
