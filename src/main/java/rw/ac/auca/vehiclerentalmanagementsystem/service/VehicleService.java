package rw.ac.auca.vehiclerentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.ParkingSlot;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Vehicle;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.ParkingSlotRepository;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired ParkingSlotRepository parkingSlotRepository;

    // CREATE
    public String saveVehicle(Vehicle vehicle) {
        if (vehicle.getMake() == null || vehicle.getMake().trim().isEmpty()) {
            return "Vehicle make cannot be empty";
        }
        if (vehicle.getModel() == null || vehicle.getModel().trim().isEmpty()) {
            return "Vehicle model cannot be empty";
        }
        if (vehicle.getPlateNumber() == null || vehicle.getPlateNumber().trim().isEmpty()) {
            return "Vehicle plate number cannot be empty";
        }

        if (vehicle.getParkingSlot() != null && vehicle.getParkingSlot().getId() != null) {
            Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(vehicle.getParkingSlot().getId());
            if (parkingSlot.isEmpty()) {
                return "The parking slot with that id does not exist";
            }
            vehicle.setParkingSlot(parkingSlot.get());
        } else {
            vehicle.setParkingSlot(null);
        }

        Optional<Vehicle> checkPlate = vehicleRepository.findByPlateNumberIgnoreCase(vehicle.getPlateNumber());
        if (checkPlate.isPresent()) {
            return "That vehicle plate number already exists";
        }

        vehicleRepository.save(vehicle);
        return "Vehicle saved successfully";
    }

    // READ ALL
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // READ ONE
    public Optional<Vehicle> getVehicleById(UUID id) {
        return vehicleRepository.findById(id);
    }

    // UPDATE
    public String updateVehicle(UUID id, Vehicle newVehicle) {
        Optional<Vehicle> existing = vehicleRepository.findById(id);

        if (existing.isEmpty()) {
            return "The vehicle with that id does not exist";
        }

        if (newVehicle.getMake() == null || newVehicle.getMake().trim().isEmpty()) {
            return "Vehicle make cannot be empty";
        }
        if (newVehicle.getModel() == null || newVehicle.getModel().trim().isEmpty()) {
            return "Vehicle model cannot be empty";
        }
        if (newVehicle.getPlateNumber() == null || newVehicle.getPlateNumber().trim().isEmpty()) {
            return "Vehicle plate number cannot be empty";
        }

        if (newVehicle.getParkingSlot() != null && newVehicle.getParkingSlot().getId() != null) {
            Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(newVehicle.getParkingSlot().getId());
            if (parkingSlot.isEmpty()) {
                return "The parking slot with that id does not exist";
            }
            newVehicle.setParkingSlot(parkingSlot.get());
        } else {
            newVehicle.setParkingSlot(null);
        }

        Optional<Vehicle> samePlate = vehicleRepository.findByPlateNumberIgnoreCase(newVehicle.getPlateNumber());
        if (samePlate.isPresent() && !samePlate.get().getId().equals(id)) {
            return "That vehicle plate number already exists";
        }

        Vehicle vehicle = existing.get();
        vehicle.setMake(newVehicle.getMake());
        vehicle.setModel(newVehicle.getModel());
        vehicle.setPlateNumber(newVehicle.getPlateNumber());
        vehicle.setParkingSlot(newVehicle.getParkingSlot());
        // DO NOT touch features
        vehicleRepository.save(vehicle);
        return "Vehicle updated successfully";
    }

    // DELETE
    public String deleteVehicle(UUID id) {
        Optional<Vehicle> existing = vehicleRepository.findById(id);

        if (existing.isEmpty()) {
            return "The vehicle with that id does not exist";
        }

        vehicleRepository.delete(existing.get());
        return "Vehicle deleted successfully";
    }
}
