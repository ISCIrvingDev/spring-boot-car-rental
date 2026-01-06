package com.ivindev.carrental.car_rental.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long jwtExpiration;

  // Genera la clave de firma basada en el secreto
  private SecretKey getSignKey() {
    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  // Generar Token de Acceso (15 min)
  public String generateToken(String username) {
    return generateToken(Map.of(), username);
  }

  private String generateToken(Map<String, Object> extraClaims, String username) {
    return Jwts.builder().claims(extraClaims).subject(username).issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + jwtExpiration)).signWith(getSignKey()).compact();
  }

  // Generar Refresh Token (Le daremos una duración mayor, ej. 30 días,
  // aunque el requisito dice refresh cada 15 min, el refresh token debe durar más
  // para ser útil)
  public String generateRefreshToken(String username) {
    return Jwts.builder().subject(username).issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 2_592_000_000L)) // 30 días
        .signWith(getSignKey()).compact();
  }

  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload();
  }

  public boolean isTokenValid(String token, String username) {
    return extractUsername(token).equals(username) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }
}