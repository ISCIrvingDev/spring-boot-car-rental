package com.ivindev.carrental.car_rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false) // Generating equals/hashCode implementation but without a call to superclass,
                                      // even though this class does not extend java.lang.Object. If this is
                                      // intentional, add '@EqualsAndHashCode(callSuper=false)' to your type
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

  private String email;
  private String name;
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  // Getters y Setters: Ya no es necesario gracias a la anotacion "@Data"
}