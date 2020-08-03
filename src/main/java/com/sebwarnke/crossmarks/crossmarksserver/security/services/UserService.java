package com.sebwarnke.crossmarks.crossmarksserver.security.services;

import com.sebwarnke.crossmarks.crossmarksserver.security.models.User;
import com.sebwarnke.crossmarks.crossmarksserver.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private PasswordEncoder passwordEncoder;
  private UserRepository userRepository;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public User createUser(User userTemplate) {
    userTemplate.setPassword(passwordEncoder.encode(userTemplate.getPassword()));
    return userRepository.save(userTemplate);
  }
}
