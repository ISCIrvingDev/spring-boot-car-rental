package com.ivindev.carrental.car_rental.repository;

import com.ivindev.carrental.car_rental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

  // Ejemplo: Encontrar vehículos por marca
  List<Vehicle> findByMaker(String maker);

  // Ejemplo: Encontrar vehículos que estén activos (esto se podría hacer con
  // filtros, pero así funciona también)
  // List<Vehicle> findByIsActive(Boolean isActive);
}