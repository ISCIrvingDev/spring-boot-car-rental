package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
@Data
@EqualsAndHashCode(callSuper = false) // Generating equals/hashCode implementation but without a call to superclass,
                                      // even though this class does not extend java.lang.Object. If this is
                                      // intentional, add '@EqualsAndHashCode(callSuper=false)' to your type
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends AbstractEntity {

  // Lob es para almacenar textos muy largos (como el base64)
  @Lob
  private String image;

  private String maker;
  private String model; // 'model' es una palabra reservada en algunos contextos, pero aquí funciona
                        // como variable
  private Integer year;
  private String color;

  @Column(precision = 10, scale = 2) // Equivalente a DECIMAL(10,2) en SQL, mejor para dinero que float
  private BigDecimal price;

  @Column(length = 1000)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "transmission_id", nullable = false)
  private Transmision transmision;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vehicle_status_id", nullable = false)
  private VehicleStatus vehicleStatus;

  // Getters y Setters: Ya no es necesario gracias a la anotacion "@Data"
}