package com.sebwarnke.crossmarks.crossmarksserver.security;

import com.sebwarnke.crossmarks.crossmarksserver.model.entities.User;
import com.sebwarnke.crossmarks.crossmarksserver.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findUserByName(username);

    optionalUser.orElseThrow(() -> new UsernameNotFoundException("Cannot find user name [" + username + "]"));

    return optionalUser.map(UserDetailsImpl::new).get();

  }
}
