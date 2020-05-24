package com.sebwarnke.crossmarks.crossmarksserver.api;

import com.sebwarnke.crossmarks.crossmarksserver.execptions.InvalidUrlException;
import com.sebwarnke.crossmarks.crossmarksserver.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @DeleteMapping("/bookmark/{id}")
  public void deleteBookmark(@PathVariable String id) {
    bookmarkService.deleteBookmark(id);
  }

  @PostMapping("/bookmark")
  public ResponseEntity<?> createBookmark(@RequestBody Bookmark bookmarkTemplate) {
    try {
      Bookmark bookmark = bookmarkService.createBookmark(bookmarkTemplate);
      return ResponseEntity.ok().body(bookmark);

    } catch (InvalidUrlException e) {
      return ResponseEntity.status(400).body("{\"message\" : \"" + e.getMessage() + "\"}");
    }
  }
}
