package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

  private String name;

  // Getters y Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
