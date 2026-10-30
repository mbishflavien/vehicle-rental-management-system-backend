package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.ParkingSlot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, UUID> {
    Optional<ParkingSlot> findBySlotNumber(int slotNumber);
    Optional<ParkingSlot> findByNameIgnoreCase(String name);
    //C2
    @Query("SELECT p FROM ParkingSlot p WHERE NOT EXISTS (SELECT v FROM Vehicle v WHERE v.parkingSlot = p) ORDER BY p.slotNumber")
    List<ParkingSlot> findFreeParkingSlots();
}
