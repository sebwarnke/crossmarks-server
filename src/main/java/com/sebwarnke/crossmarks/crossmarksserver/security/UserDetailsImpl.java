package com.sebwarnke.crossmarks.crossmarksserver.security;

import com.sebwarnke.crossmarks.crossmarksserver.model.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

  private final String userName;
  private final String password;
  private final Boolean isActive;
  private final List<GrantedAuthority> authorities;

  public UserDetailsImpl(User user) {
    this.userName = user.getName();
    this.password = user.getPassword();
    this.isActive = true;
    this.authorities = Arrays.stream(user.getRoles().split(","))
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
