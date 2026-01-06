package com.ivindev.carrental.car_rental.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class SignUpRequest {

  @NotBlank
  private String name;

  @NotBlank
  private String lastName;

  @NotBlank
  private String userName;

  @Email
  private String email;

  private LocalDateTime dateOfBirth;

  @NotBlank
  @Size(min = 8, message = "The password must be at least 8 characters long")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "The password must contain uppercase letters, lowercase letters, numbers and symbols")
  private String password;
}