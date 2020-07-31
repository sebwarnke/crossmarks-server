package com.sebwarnke.crossmarks.crossmarksserver.core.services;

import com.sebwarnke.crossmarks.crossmarksserver.core.exceptions.InvalidUrlException;
import com.sebwarnke.crossmarks.crossmarksserver.core.exceptions.NoSuchBookmarkException;
import com.sebwarnke.crossmarks.crossmarksserver.core.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.core.model.repositories.BookmarksRepository;
import com.sebwarnke.crossmarks.crossmarksserver.core.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {
  private static final Logger log = LoggerFactory.getLogger(BookmarkService.class);

  private BookmarksRepository bookmarksRepository;

  @Autowired
  public BookmarkService(BookmarksRepository bookmarksRepository) {
    this.bookmarksRepository = bookmarksRepository;
  }

  /**
   * Creates and stores a Bookmark
   * @param bookmarkTemplate Template of the bookmark that contains name, url and, optionally, description
   * @return The created Entity
   */
  public Bookmark createBookmark(Bookmark bookmarkTemplate) throws InvalidUrlException {

    if (!Util.isValidUrl(bookmarkTemplate.getUrl())) {
      throw new InvalidUrlException(bookmarkTemplate.getUrl());
    }

    Bookmark bookmark = Bookmark.builder()
      .name(bookmarkTemplate.getName())
      .url(bookmarkTemplate.getUrl())
      .description(bookmarkTemplate.getDescription())
      .createdAt(LocalDateTime.now())
      .build();

    log.debug("Saving Bookmark:");
    log.debug(bookmark.toString());

    return bookmarksRepository.save(bookmark);
  }

  public void deleteBookmark(String id) {
    log.debug("Deleting Bookmark with ID [{}]", id);
    bookmarksRepository.deleteById(id);
  }

  public Bookmark getBookmark(String id) throws NoSuchBookmarkException {
    Optional<Bookmark> optional = bookmarksRepository.findById(id);
    if (optional.isEmpty()) {
      throw new NoSuchBookmarkException(id);
    }
    return optional.get();
  }

  /**
   * Returns all Bookmark Entities
   * @return List of all Bookmarks
   */
  public List<Bookmark> getAllBookmarks() {
    return bookmarksRepository.findAll();
  }

  public Bookmark updateBookmark(Bookmark bookmarkTemplate) throws NoSuchBookmarkException {

    Optional<Bookmark> optional = bookmarksRepository.findById(bookmarkTemplate.getId());

    if (optional.isEmpty()) {
      throw new NoSuchBookmarkException(bookmarkTemplate.getId());
    } else {
      Bookmark bookmark = optional.get();
      bookmark.setName(bookmarkTemplate.getName());
      bookmark.setUrl(bookmarkTemplate.getUrl());
      bookmark.setDescription(bookmarkTemplate.getDescription());

      log.debug("Updating Bookmark:");
      log.debug(bookmark.toString());
      return bookmarksRepository.save(bookmark);
    }
  }
}
