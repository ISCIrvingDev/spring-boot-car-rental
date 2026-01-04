package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_status")
public class VehicleStatus extends AbstractEntity {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}