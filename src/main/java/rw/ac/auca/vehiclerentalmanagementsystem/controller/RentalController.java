package rw.ac.auca.vehiclerentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.Rental;
import rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus;
import rw.ac.auca.vehiclerentalmanagementsystem.service.RentalService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/save")
    public ResponseEntity<?> saveRental(@RequestBody Rental rental) {
        String returnedMessage = rentalService.saveRental(rental);
        if (returnedMessage.equals("Rental saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else if(returnedMessage.equals("Rental date cannot be in the past")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }else if(returnedMessage.equals("Vehicle is already rented on that date")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<Rental>> getAllRentals() {
        return new ResponseEntity<>(rentalService.getAllRentals(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalById(@PathVariable UUID id) {
        Optional<Rental> rental = rentalService.getRentalById(id);
        if (rental.isPresent()) {
            return new ResponseEntity<>(rental.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The rental with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRental(@PathVariable UUID id, @RequestBody Rental rental) {
        String returnedMessage = rentalService.updateRental(id, rental);
        if (returnedMessage.equals("Rental updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The rental with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable UUID id) {
        String returnedMessage = rentalService.deleteRental(id);
        if (returnedMessage.equals("Rental deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The rental with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<Rental>> getRentalsByStatus(@RequestParam RentalStatus status){
        return new ResponseEntity<>(rentalService.getRentalsByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<Rental>> getRentalsBetween(@RequestParam String start, @RequestParam String end){
        java.time.LocalDate startDate = java.time.LocalDate.parse(start.trim());
        java.time.LocalDate endDate = java.time.LocalDate.parse(end.trim());
        return new ResponseEntity<>(rentalService.getRentalsBetween(startDate, endDate), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateRentalStatus(@PathVariable java.util.UUID id, @RequestParam rw.ac.auca.vehiclerentalmanagementsystem.domain.RentalStatus status){
        String returnedMessage = rentalService.updateRentalStatus(id, status);
        if(returnedMessage.equals("Rental status updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("The rental with that id does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }
}