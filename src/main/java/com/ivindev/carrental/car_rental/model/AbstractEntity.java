package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

// @MappedSuperclass le dice a Hibernate: "No crees una tabla para esto, solo usa sus campos en las tablas hijas"
@MappedSuperclass
// EntityListener escucha los cambios para actualizar las fechas automáticamente
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID) // Genera UUID automáticamente
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "is_active", columnDefinition = "boolean default true")
  private Boolean isActive = true;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // Getters y Setters
  public UUID getId() {
    return id;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
