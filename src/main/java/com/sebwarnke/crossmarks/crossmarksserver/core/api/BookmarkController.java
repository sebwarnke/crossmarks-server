package com.sebwarnke.crossmarks.crossmarksserver.core.api;

import com.sebwarnke.crossmarks.crossmarksserver.core.execptions.InvalidUrlException;
import com.sebwarnke.crossmarks.crossmarksserver.core.execptions.NoSuchBookmarkException;
import com.sebwarnke.crossmarks.crossmarksserver.core.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.core.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/bookmark/{id}")
  public ResponseEntity<?> getBookmarkById(@PathVariable String id) {
    try {
      return ResponseEntity.ok().body(bookmarkService.getBookmark(id));

    } catch (NoSuchBookmarkException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\" : \"" + e.getMessage() + "\"}");
    }
  }

  @DeleteMapping("/bookmark/{id}")
  public void deleteBookmark(@PathVariable String id) {
    bookmarkService.deleteBookmark(id);
  }

  @PostMapping("/bookmark")
  public ResponseEntity<?> createBookmark(@RequestBody Bookmark bookmarkTemplate) {
    try {
      return ResponseEntity.ok().body(bookmarkService.createBookmark(bookmarkTemplate));

    } catch (InvalidUrlException e) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"message\" : \"" + e.getMessage() + "\"}");
    }
  }
}
