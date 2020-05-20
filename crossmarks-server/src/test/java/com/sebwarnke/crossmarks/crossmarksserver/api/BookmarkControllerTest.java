package com.sebwarnke.crossmarks.crossmarksserver.api;

import com.sebwarnke.crossmarks.crossmarksserver.services.BookmarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

}
