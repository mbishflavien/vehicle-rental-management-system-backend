package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Renter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RenterRepository extends JpaRepository<Renter, UUID> {
    Optional<Renter> findByLicenseNumberIgnoreCase(String licenseNumber);
    List<Renter> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
