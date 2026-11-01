package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Rental;

import java.util.List;
import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
    List<Rental> findByStatusOrderByPickupDateAsc(rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus status);
    List<Rental> findByPickupDateBetweenOrderByPickupDateAsc(java.time.LocalDate start, java.time.LocalDate end);
    boolean existsByVehicleIdAndPickupDateAndStatusNot(java.util.UUID vehicleId, java.time.LocalDate pickupDate, rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus status);

    @Query("SELECT r.status, COUNT(r) FROM Rental r GROUP BY r.status")
    List<Object[]> countRentalsByStatus();

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query("UPDATE Rental r SET r.status = rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus.CANCELLED WHERE r.vehicle.id = :vehicleId AND r.pickupDate = :date AND r.status <> rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus.RETURNED") int cancelRentalsForVehicleOnDate(@Param("vehicleId") java.util.UUID vehicleId, @Param("date") java.time.LocalDate date);
}
