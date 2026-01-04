package com.ivindev.carrental.car_rental.repository;

import com.ivindev.carrental.car_rental.model.BookVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookVehicleRepository extends JpaRepository<BookVehicle, UUID> {
  // Métodos heredados disponibles: save, findAll, delete, etc.
}