package com.ivindev.carrental.car_rental.repository;

import com.ivindev.carrental.car_rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  // * Aquí agregaremos un método personalizado, ya que es común buscar por email.
  // Spring Data JPA analiza el nombre del método y genera la query SQL
  // automáticamente.
  // Equivalente a: SELECT * FROM users WHERE email = ?
  Optional<User> findByEmail(String email);
}