package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "book_vehicles")
@Data
@EqualsAndHashCode(callSuper = false) // Generating equals/hashCode implementation but without a call to superclass,
                                      // even though this class does not extend java.lang.Object. If this is
                                      // intentional, add '@EqualsAndHashCode(callSuper=false)' to your type
@NoArgsConstructor
@AllArgsConstructor
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

  // Getters y Setters: Ya no es necesario gracias a la anotacion "@Data"
}