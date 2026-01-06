package com.ivindev.carrental.car_rental.service;

import com.ivindev.carrental.car_rental.dto.AuthResponse;
import com.ivindev.carrental.car_rental.dto.LoginRequest;
import com.ivindev.carrental.car_rental.dto.SignUpRequest;
import com.ivindev.carrental.car_rental.model.Role;
import com.ivindev.carrental.car_rental.model.User;
import com.ivindev.carrental.car_rental.repository.RoleRepository;
import com.ivindev.carrental.car_rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  // Sign Up
  public AuthResponse register(SignUpRequest request) {
    // 1. Verificar si existe
    if (userRepository.findByUserName(request.getUserName()).isPresent()) {
      throw new RuntimeException("El usuario ya existe");
    }

    // 2. Buscar rol
    Role userRole = roleRepository.findByName("User")
        .orElseThrow(() -> new RuntimeException("Rol por defecto no encontrado"));

    // 3. Crear Usuario
    User user = new User();
    user.setName(request.getName());
    user.setLastName(request.getLastName());
    user.setUserName(request.getUserName());
    user.setEmail(request.getEmail());
    user.setDateOfBirth(request.getDateOfBirth());
    user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash
    user.setRole(userRole);
    user.setIsActive(true);

    userRepository.save(user);

    // 4. Generar Tokens
    var accessToken = jwtService.generateToken(user.getUserName());
    var refreshToken = jwtService.generateRefreshToken(user.getUserName());

    return new AuthResponse(accessToken, refreshToken);
  }

  // Login / Sign In
  public AuthResponse authenticate(LoginRequest request) {
    // 1. Autenticar (Spring lo hace, lanza error si falla)
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

    // 2. Generar Tokens
    var user = userRepository.findByUserName(request.getUserName())
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    var accessToken = jwtService.generateToken(user.getUserName());
    var refreshToken = jwtService.generateRefreshToken(user.getUserName());

    return new AuthResponse(accessToken, refreshToken);
  }

  // Refresh Token
  public AuthResponse refreshToken(String refreshToken) {
    String username = jwtService.extractUsername(refreshToken);

    if (username != null && jwtService.isTokenValid(refreshToken, username)) {
      // Generamos un nuevo access token (no nuevo refresh token, aunque se puede)
      var newAccessToken = jwtService.generateToken(username);
      // Retornamos el mismo refresh token para seguir usándolo
      return new AuthResponse(newAccessToken, refreshToken);
    }
    throw new RuntimeException("Token de refresco inválido o expirado");
  }
}