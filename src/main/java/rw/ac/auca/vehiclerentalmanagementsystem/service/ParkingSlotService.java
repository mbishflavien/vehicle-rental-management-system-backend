package rw.ac.auca.vehiclerentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.ParkingSlot;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.ParkingSlotRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    // CREATE
    public String saveParkingSlot(ParkingSlot parkingSlot) {
        if (parkingSlot.getSlotNumber() <= 0) {
            return "Slot number cannot be empty";
        }
        if (parkingSlot.getName() == null || parkingSlot.getName().trim().isEmpty()) {
            return "Slot name cannot be empty";
        }

        Optional<ParkingSlot> checkNumber = parkingSlotRepository.findBySlotNumber(parkingSlot.getSlotNumber());
        if (checkNumber.isPresent()) {
            return "That parking slot number already exists";
        }

        Optional<ParkingSlot> checkName = parkingSlotRepository.findByNameIgnoreCase(parkingSlot.getName());
        if (checkName.isPresent()) {
            return "That parking slot name already exists";
        }

        parkingSlotRepository.save(parkingSlot);
        return "Parking slot saved successfully";
    }

    // READ ALL
    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotRepository.findAll();
    }

    // READ ONE
    public Optional<ParkingSlot> getParkingSlotById(UUID id) {
        return parkingSlotRepository.findById(id);
    }

    // UPDATE
    public String updateParkingSlot(UUID id, ParkingSlot newParkingSlot) {
        Optional<ParkingSlot> existing = parkingSlotRepository.findById(id);

        if (existing.isEmpty()) {
            return "The parking slot with that id does not exist";
        }

        if (newParkingSlot.getSlotNumber() <= 0) {
            return "Slot number cannot be empty";
        }
        if (newParkingSlot.getName() == null || newParkingSlot.getName().trim().isEmpty()) {
            return "Slot name cannot be empty";
        }

        ParkingSlot parkingSlot = existing.get();
        parkingSlot.setSlotNumber(newParkingSlot.getSlotNumber());
        parkingSlot.setName(newParkingSlot.getName());
        parkingSlotRepository.save(parkingSlot);
        return "Parking slot updated successfully";
    }

    // DELETE
    public String deleteParkingSlot(UUID id) {
        Optional<ParkingSlot> existing = parkingSlotRepository.findById(id);

        if (existing.isEmpty()) {
            return "The parking slot with that id does not exist";
        }

        parkingSlotRepository.delete(existing.get());
        return "Parking slot deleted successfully";
    }
}
