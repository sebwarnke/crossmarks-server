package com.sebwarnke.crossmarks.crossmarksserver.security.util;

import com.sebwarnke.crossmarks.crossmarksserver.CrossmarksConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtUtil {

  private CrossmarksConfiguration crossmarksConfiguration;

  @Autowired
  public JwtUtil(CrossmarksConfiguration crossmarksConfiguration) {
    this.crossmarksConfiguration = crossmarksConfiguration;
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Claims extractAllClaims(final String token) {
    return Jwts.parser().setSigningKey(crossmarksConfiguration.getJwtSecret()).parseClaimsJws(token).getBody();
  }

  public String generateToken(UserDetails userDetails) {
    HashMap<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(final HashMap<String, Object> claims, final String subject) {
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 24 * 60 * 60))
      .signWith(SignatureAlgorithm.HS256, crossmarksConfiguration.getJwtSecret())
      .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
