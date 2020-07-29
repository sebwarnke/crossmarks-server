package com.sebwarnke.crossmarks.crossmarksserver.core.api;

import com.sebwarnke.crossmarks.crossmarksserver.core.exceptions.InvalidUrlException;
import com.sebwarnke.crossmarks.crossmarksserver.core.exceptions.NoSuchBookmarkException;
import com.sebwarnke.crossmarks.crossmarksserver.core.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.core.services.BookmarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BookmarkController {
  public static final Logger log = LoggerFactory.getLogger(BookmarkController.class);

  private BookmarkService bookmarkService;

  @Autowired
  public BookmarkController(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @GetMapping("/bookmark")
  public Collection<Bookmark> getAllBookmarks() {
    log.debug("Endpoint called: GET /bookmark");
    return bookmarkService.getAllBookmarks();
  }

  @GetMapping("/bookmark/{id}")
  public ResponseEntity<?> getBookmarkById(@PathVariable String id) {
    log.debug("Endpoint called: GET /bookmark/{}", id);
    try {
      log.debug("-- Entity found");
      return ResponseEntity.ok().body(bookmarkService.getBookmark(id));

    } catch (NoSuchBookmarkException e) {
      log.debug("-- Entity not found");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\" : \"" + e.getMessage() + "\"}");
    }
  }

  @DeleteMapping("/bookmark/{id}")
  public void deleteBookmark(@PathVariable String id) {
    log.debug("Endpoint called: DELETE /bookmark/{}", id);
    bookmarkService.deleteBookmark(id);
  }

  @PostMapping("/bookmark")
  public ResponseEntity<?> createBookmark(@RequestBody Bookmark bookmarkTemplate) {
    log.debug("Endpoint called: POST /bookmark");
    try {
      log.debug("-- Entity created");
      return ResponseEntity.ok().body(bookmarkService.createBookmark(bookmarkTemplate));

    } catch (InvalidUrlException e) {
      log.debug("Entity not created; {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"message\" : \"" + e.getMessage() + "\"}");
    }
  }

  @PutMapping("/bookmark/{id}")
  public ResponseEntity<?> updateBookmark(@PathVariable String id, @RequestBody Bookmark bookmarkTemplate) {
    log.debug("Endpoint called: PUT /bookmark/{}", id);
    try {
      log.debug("-- Entity updated");
      return ResponseEntity.ok().body(bookmarkService.updateBookmark(id, bookmarkTemplate));

    } catch (NoSuchBookmarkException e) {
      log.debug("-- Entity not updated");
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("{\"message\" : \"" + e.getMessage() + "\"}");
    }

  }
}
