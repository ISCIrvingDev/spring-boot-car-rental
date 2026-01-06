package com.ivindev.carrental.car_rental.security;

import com.ivindev.carrental.car_rental.model.User;
import com.ivindev.carrental.car_rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username) // Asegúrate de crear este metodo en UserRepository
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

    // return new CustomUserDetails(user);
    return new org.springframework.security.core.userdetails.User(
        // user.getUsername(),
        user.getUserName(), user.getPassword(),
        // user.getRoles().stream()
        // .map(role -> new SimpleGrantedAuthority(role.getName()))
        // .toList()
        Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())));
  }
}