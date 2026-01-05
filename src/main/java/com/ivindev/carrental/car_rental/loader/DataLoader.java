package com.ivindev.carrental.car_rental.loader;

import com.ivindev.carrental.car_rental.model.Role;
import com.ivindev.carrental.car_rental.model.Transmision;
import com.ivindev.carrental.car_rental.model.VehicleStatus;
import com.ivindev.carrental.car_rental.repository.RoleRepository;
import com.ivindev.carrental.car_rental.repository.TransmisionRepository;
import com.ivindev.carrental.car_rental.repository.VehicleStatusRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// @Component le dice a Spring: "Oye, crea una instancia de esta clase al inicio"
// CommandLineRunner le dice: "Ejecuta el método 'run' cuando la aplicación arranque"
@Component
@RequiredArgsConstructor // Genera el constructor automáticamente con las dependencias 'final'
public class DataLoader implements CommandLineRunner {

  private final RoleRepository roleRepository;
  private final TransmisionRepository transmisionRepository;
  private final VehicleStatusRepository vehicleStatusRepository;

  @Override
  public void run(String... args) throws Exception {
    loadRoles();
    loadTransmissions();
    loadVehicleStatus();
  }

  private void loadRoles() {
    // Verificamos si ya existen para no duplicar datos
    if (roleRepository.count() == 0) {
      Role admin = new Role();
      admin.setName("Admin");

      Role user = new Role();
      user.setName("User");

      roleRepository.saveAll(List.of(admin, user));
      System.out.println("🌱 Roles inicializados: Admin, User");
    } else {
      System.out.println("ℹ️ Roles ya existentes. Se omitió carga inicial.");
    }
  }

  private void loadTransmissions() {
    if (transmisionRepository.count() == 0) {
      Transmision automatic = new Transmision();
      automatic.setName("Automatic");

      Transmision manual = new Transmision();
      manual.setName("Manual");

      transmisionRepository.saveAll(List.of(automatic, manual));
      System.out.println("🌱 Transmisiones inicializadas: Automatic, Manual");
    } else {
      System.out.println("ℹ️ Transmisiones ya existentes. Se omitió carga inicial.");
    }
  }

  private void loadVehicleStatus() {
    if (vehicleStatusRepository.count() == 0) {
      VehicleStatus pending = new VehicleStatus();
      pending.setName("Pending");

      VehicleStatus rented = new VehicleStatus();
      rented.setName("Rented");

      VehicleStatus rejected = new VehicleStatus();
      rejected.setName("Rejected");

      vehicleStatusRepository.saveAll(List.of(pending, rented, rejected));
      System.out.println("🌱 Estatus inicializados: Pending, Rented, Rejected");
    } else {
      System.out.println("ℹ️ Estatus ya existentes. Se omitió carga inicial.");
    }
  }
}