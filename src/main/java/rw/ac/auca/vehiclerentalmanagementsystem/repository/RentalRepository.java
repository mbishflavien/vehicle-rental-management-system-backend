package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Rental;

import java.util.List;
import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
    List<Rental> findByStatusOrderByPickupDateAsc(rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus status);
    List<Rental> findByPickupDateBetweenOrderByPickupDateAsc(java.time.LocalDate start, java.time.LocalDate end);
}
