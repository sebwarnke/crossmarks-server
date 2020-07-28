package com.sebwarnke.crossmarks.crossmarksserver.core.model.repositories;

import com.sebwarnke.crossmarks.crossmarksserver.core.model.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarksRepository extends JpaRepository<Bookmark, String> {}
