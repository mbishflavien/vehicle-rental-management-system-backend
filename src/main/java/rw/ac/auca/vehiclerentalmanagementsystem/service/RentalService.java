package rw.ac.auca.vehiclerentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Rental;
import rw.ac.auca.vehiclerentalmanagementsystem.repository.RentalRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    // CREATE
    public String saveRental(Rental rental) {
        if (rental.getPickupDate() == null) {
            return "Pickup date cannot be empty";
        }
        if (rental.getRenter() == null || rental.getRenter().getId() == null) {
            return "Rental must be linked to a renter";
        }
        if (rental.getVehicle() == null || rental.getVehicle().getId() == null) {
            return "Rental must be linked to a vehicle";
        }
        if (rental.getDays() <= 0) {
            return "Days must be greater than 0";
        }
        if (rental.getDailyRate() <= 0) {
            return "Daily rate must be greater than 0";
        }
        rentalRepository.save(rental);
        return "Rental saved successfully";
    }

    // READ ALL
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // READ ONE
    public Optional<Rental> getRentalById(UUID id) {
        return rentalRepository.findById(id);
    }

    // UPDATE
    public String updateRental(UUID id, Rental newRental) {
        Optional<Rental> existing = rentalRepository.findById(id);

        if (existing.isEmpty()) {
            return "The rental with that id does not exist";
        }

        Rental rental = existing.get();
        rental.setPickupDate(newRental.getPickupDate());
        rental.setDays(newRental.getDays());
        rental.setDailyRate(newRental.getDailyRate());
        rental.setRenter(newRental.getRenter());
        rental.setVehicle(newRental.getVehicle());
        rentalRepository.save(rental);
        return "Rental updated successfully";
    }

    // DELETE
    public String deleteRental(UUID id) {
        Optional<Rental> existing = rentalRepository.findById(id);

        if (existing.isEmpty()) {
            return "The rental with that id does not exist";
        }

        rentalRepository.delete(existing.get());
        return "Rental deleted successfully";
    }
}
