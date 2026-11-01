package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalCharge;

import java.util.List;
import java.util.UUID;

@Repository
public interface RentalChargeRepository extends JpaRepository<RentalCharge, UUID> {
    @Query("SELECT rc FROM RentalCharge rc WHERE rc.rental.renter.licenseNumber = :licenseNumber ORDER BY rc.chargedDate DESC")
    List<RentalCharge> findByRenterLicenseNumber(@Param("licenseNumber") String licenseNumber);

    boolean existsByRentalId(UUID rentalId);
}
