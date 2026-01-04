package com.ivindev.carrental.car_rental.repository;

import com.ivindev.carrental.car_rental.model.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, UUID> {
  VehicleStatus findByName(String name);
}