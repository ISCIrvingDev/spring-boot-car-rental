package com.ivindev.carrental.car_rental.controller;

import com.ivindev.carrental.car_rental.dto.AuthResponse;
import com.ivindev.carrental.car_rental.dto.LoginRequest;
import com.ivindev.carrental.car_rental.dto.SignUpRequest;
import com.ivindev.carrental.car_rental.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permitir llamadas desde el frontend
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.authenticate(request));
  }

  @PostMapping("/sign-in")
  public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest request) {
    // Normalmente sign-in y login son lo mismo, usamos el mismo método
    return ResponseEntity.ok(authService.authenticate(request));
  }

  @PostMapping("/sign-up")
  public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new RuntimeException("Token no proporcionado");
    }
    String refreshToken = authHeader.substring(7);
    return ResponseEntity.ok(authService.refreshToken(refreshToken));
  }
}
