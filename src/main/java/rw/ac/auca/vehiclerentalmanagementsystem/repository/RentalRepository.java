package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Rental;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
}
