package com.ivindev.carrental.car_rental.repository;

import com.ivindev.carrental.car_rental.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
  // Ya tienes métodos como: findAll(), save(), deleteById(), findById()

  // Ejemplo de método derivado automático (similar a Prisma/TypeORM):
  // Role findByName(String name);
}