package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByPlateNumberIgnoreCase(String plateNumber);
    //C1
    @Query("SELECT v FROM Vehicle v JOIN v.features f WHERE LOWER(f.name) = LOWER(:name)")
    List<Vehicle> findVehiclesByFeatureName(@Param("name") String name);
}
