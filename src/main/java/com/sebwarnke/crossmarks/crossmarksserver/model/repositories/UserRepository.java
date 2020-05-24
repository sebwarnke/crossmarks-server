package com.sebwarnke.crossmarks.crossmarksserver.model.repositories;

import com.sebwarnke.crossmarks.crossmarksserver.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findUserByName(String name);
}
