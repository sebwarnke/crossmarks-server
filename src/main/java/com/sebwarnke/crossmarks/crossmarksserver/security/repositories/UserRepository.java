package com.sebwarnke.crossmarks.crossmarksserver.security.repositories;

import com.sebwarnke.crossmarks.crossmarksserver.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findUserByUsername(String username);
}
