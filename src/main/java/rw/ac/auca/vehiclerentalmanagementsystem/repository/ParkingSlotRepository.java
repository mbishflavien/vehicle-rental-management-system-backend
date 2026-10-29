package rw.ac.auca.vehiclerentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.ParkingSlot;

import java.util.Optional;
import java.util.UUID;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, UUID> {
    Optional<ParkingSlot> findBySlotNumber(int slotNumber);
    Optional<ParkingSlot> findByNameIgnoreCase(String name);
}
