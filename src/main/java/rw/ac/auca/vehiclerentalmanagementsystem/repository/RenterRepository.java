package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Renter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RenterRepository extends JpaRepository<Renter, UUID> {
    Optional<Renter> findByLicenseNumberIgnoreCase(String licenseNumber);
    List<Renter> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query("SELECT DISTINCT r FROM Rental ren JOIN ren.renter r WHERE ren.vehicle.id = :vehicleId")
    List<Renter> findRentersByVehicleId(@Param("vehicleId") java.util.UUID vehicleId);
}
