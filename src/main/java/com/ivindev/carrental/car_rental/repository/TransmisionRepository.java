package com.ivindev.carrental.car_rental.repository;

import com.ivindev.carrental.car_rental.model.Transmision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransmisionRepository extends JpaRepository<Transmision, UUID> {
  // No necesitamos métodos extra por ahora, heredamos todo lo necesario.
}