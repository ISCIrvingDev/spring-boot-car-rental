package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "book_vehicles")
public class BookVehicle extends AbstractEntity {

  @Column(precision = 10, scale = 2)
  private BigDecimal price;

  private Integer days;

  private LocalDate rentalStartDate;
  private LocalDate rentalEndDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // Getters y Setters
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public LocalDate getRentalStartDate() {
    return rentalStartDate;
  }

  public void setRentalStartDate(LocalDate rentalStartDate) {
    this.rentalStartDate = rentalStartDate;
  }

  public LocalDate getRentalEndDate() {
    return rentalEndDate;
  }

  public void setRentalEndDate(LocalDate rentalEndDate) {
    this.rentalEndDate = rentalEndDate;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}