package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
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

  // Getters y Setters
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Transmision getTransmision() {
    return transmision;
  }

  public void setTransmision(Transmision transmision) {
    this.transmision = transmision;
  }

  public VehicleStatus getVehicleStatus() {
    return vehicleStatus;
  }

  public void setVehicleStatus(VehicleStatus vehicleStatus) {
    this.vehicleStatus = vehicleStatus;
  }
}