package com.sebwarnke.crossmarks.crossmarksserver.services;

import com.sebwarnke.crossmarks.crossmarksserver.execptions.InvalidUrlException;
import com.sebwarnke.crossmarks.crossmarksserver.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.model.repositories.BookmarksRepository;
import com.sebwarnke.crossmarks.crossmarksserver.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookmarkService {

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

    return bookmarksRepository.save(bookmark);
  }

  /**
   * Returns all Bookmark Entities
   * @return List of all Bookmarks
   */
  public List<Bookmark> getAllBookmarks() {
    return bookmarksRepository.findAll();
  }
}
