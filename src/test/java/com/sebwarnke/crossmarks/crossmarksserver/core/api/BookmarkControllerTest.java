package com.sebwarnke.crossmarks.crossmarksserver.core.api;

import com.sebwarnke.crossmarks.crossmarksserver.core.model.entities.Bookmark;
import com.sebwarnke.crossmarks.crossmarksserver.core.services.BookmarkService;
import com.sebwarnke.crossmarks.crossmarksserver.security.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookmarkController.class)
public class BookmarkControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserDetailsServiceImpl userDetailsService;

  @MockBean
  private BookmarkService bookmarkService;

  @Test
  public void givenNoBookmarksStored_whenGetBookmarks_thenReturnEmptyCollection() throws Exception {
    given(bookmarkService.getAllBookmarks()).willReturn(Collections.emptyList());

    mvc.perform(
      get("/api/bookmark")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void givenTwoBookmarksStored_whenGetBookmarks_thenReturnCollectionOfTwo() throws Exception {
    Bookmark b1 = Bookmark.builder()
      .name("b1")
      .url("www.sebwarnke.com")
      .build();
    Bookmark b2 = Bookmark.builder()
      .name("b2")
      .url("www.lewarnke.com")
      .build();

    given(bookmarkService.getAllBookmarks()).willReturn(Arrays.asList(b1, b2));

    mvc.perform(
      get("/api/bookmark")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)));
  }

}
