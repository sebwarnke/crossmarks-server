package com.sebwarnke.crossmarks.crossmarksserver.security.api;

import com.sebwarnke.crossmarks.crossmarksserver.core.model.entities.MessageResponse;
import com.sebwarnke.crossmarks.crossmarksserver.security.models.AuthenticationRequest;
import com.sebwarnke.crossmarks.crossmarksserver.security.models.AuthenticationResponse;
import com.sebwarnke.crossmarks.crossmarksserver.security.services.UserDetailsServiceImpl;
import com.sebwarnke.crossmarks.crossmarksserver.security.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/api/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

    log.debug("Authentication requested for [{}]", authenticationRequest.getUsername());

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      ));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse(e.getMessage()));
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

    String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok().body(new AuthenticationResponse(jwt));
  }

}
